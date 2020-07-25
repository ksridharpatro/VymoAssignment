package com.example.vymoassignment.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.network.GithubIssuesWebservice;
import com.example.vymoassignment.network.RetrofitNetworkClient;
import com.example.vymoassignment.util.Resource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;

public class GithubIssueRepository {
    private Executor executor = Executors.newFixedThreadPool(10);
    private GithubIssuesWebservice githubIssuesWebservice =
            RetrofitNetworkClient.getRetrofitInstance().create(GithubIssuesWebservice.class);

    public LiveData<Resource<List<GithubIssue>>> getIssues(
            String orgName,
            String repoName,
            String state) {

        final MutableLiveData<Resource<List<GithubIssue>>> resultState = new MutableLiveData<>();
        resultState.postValue(new Resource.Loading<>());
        executor.execute(() -> {
            try {
                Response<List<GithubIssue>> response =
                        githubIssuesWebservice.getGithubIssues(orgName, repoName, state).execute();
                resultState.postValue(new Resource.Success<>(response.body()));
            } catch (IOException e) {
                resultState.postValue(new Resource.Error<>(e.getMessage()));
            }
        });
        return resultState;
    }
}
