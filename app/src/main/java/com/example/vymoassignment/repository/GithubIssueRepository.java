package com.example.vymoassignment.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vymoassignment.database.DatabaseClient;
import com.example.vymoassignment.database.GithubIssueReqEntity;
import com.example.vymoassignment.database.GithubIssuesDao;
import com.example.vymoassignment.database.GithubRepoDao;
import com.example.vymoassignment.enums.GithubIssueType;
import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.model.GithubRepoDetails;
import com.example.vymoassignment.network.GithubIssuesWebservice;
import com.example.vymoassignment.network.RetrofitNetworkClient;
import com.example.vymoassignment.util.Resource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GithubIssueRepository {
    private static final long REFRESH_TIME_OUT_MILLIS = 5 * 60 * 1000;
    private Executor executor;
    private GithubIssuesWebservice githubIssuesWebservice;
    private GithubIssuesDao githubIssuesDao;
    private GithubRepoDao githubRepoDao;
    private MutableLiveData<Resource<List<GithubIssue>>> resultState;

    public GithubIssueRepository(Application application) {
        this.executor = Executors.newFixedThreadPool(10);
        this.githubIssuesWebservice =
                RetrofitNetworkClient.getRetrofitInstance().create(GithubIssuesWebservice.class);
        this.githubIssuesDao = DatabaseClient.getAppDatabaseInstance(application).githubIssuesDao();
        this.githubRepoDao = DatabaseClient.getAppDatabaseInstance(application).githubRepoDao();
        resultState = new MutableLiveData<>();
    }

    public LiveData<Resource<List<GithubIssue>>> getIssues(final GithubRepoDetails githubRepoDetails,
                                                           final GithubIssueType githubIssueType) {
        resultState.postValue(new Resource.Loading<>());
        executor.execute(() -> fetchIssues(githubRepoDetails, githubIssueType));
        return resultState;
    }

    private void fetchIssues(GithubRepoDetails githubRepoDetails, GithubIssueType githubIssueType) {
        if (shouldFetchIssuesFromCache(githubRepoDetails, githubIssueType)) {
            fetchIssuesFromCache(githubRepoDetails, githubIssueType);
        } else {
            fetchIssuesFromNetworkAndCacheIt(githubRepoDetails, githubIssueType);
        }
    }

    private void fetchIssuesFromCache(GithubRepoDetails githubRepoDetails, GithubIssueType githubIssueType) {
        resultState.postValue(new Resource.Success<>(
                githubIssuesDao.findAllByRepoId(generateIssueReqId(githubRepoDetails, githubIssueType))));
    }

    private void fetchIssuesFromNetworkAndCacheIt(GithubRepoDetails githubRepoDetails,
                                                  GithubIssueType githubIssueType) {
        List<GithubIssue> issuesFromNetWork;
        try {
            issuesFromNetWork = githubIssuesWebservice.getGithubIssues(
                    githubRepoDetails.getOwnerName(),
                    githubRepoDetails.getRepoName(),
                    githubIssueType.getTypeString()).execute().body();
            long fetchedTimeStump = System.currentTimeMillis();
            if (issuesFromNetWork != null) {
                for (GithubIssue githubIssue : issuesFromNetWork) {
                    githubIssue.setRepoId(generateIssueReqId(githubRepoDetails, githubIssueType));
                }
                //Clear existing data form Database If any old data 
                githubIssuesDao.deleteByRepoId(generateIssueReqId(githubRepoDetails, githubIssueType));
                githubRepoDao.deleteByRepoId(generateIssueReqId(githubRepoDetails, githubIssueType));

                //Save new Data into Database
                githubIssuesDao.insertAll(issuesFromNetWork);
                githubRepoDao.insert(new GithubIssueReqEntity(
                        generateIssueReqId(githubRepoDetails, githubIssueType),
                        fetchedTimeStump));

                resultState.postValue(new Resource.Success<>(issuesFromNetWork));
            } else {
                resultState.postValue(new Resource.Error<>("No Result"));
            }
        } catch (IOException e) {
            resultState.postValue(new Resource.Error<>(e.getMessage()));
        }
    }

    private boolean shouldFetchIssuesFromCache(GithubRepoDetails githubRepoDetails, GithubIssueType githubIssueType) {
        GithubIssueReqEntity githubIssueReqEntity =
                githubRepoDao.getRepoById(generateIssueReqId(githubRepoDetails, githubIssueType));
        return githubIssueReqEntity != null &&
                timeOut(githubIssueReqEntity.getFetchTimestamp());
    }

    private boolean timeOut(long lastFetchTimeStump) {
        long currentTimeStump = System.currentTimeMillis();
        long timeStumpDiff = currentTimeStump - lastFetchTimeStump;
        return timeStumpDiff > REFRESH_TIME_OUT_MILLIS;
    }

    private String generateIssueReqId(GithubRepoDetails githubRepoDetails,
                                      GithubIssueType githubIssueType) {

        return String.format("%s_%s_%s",
                githubRepoDetails.getOwnerName(),
                githubRepoDetails.getRepoName(),
                githubIssueType.getTypeString());
    }
}
