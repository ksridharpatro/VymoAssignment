package com.example.vymoassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.vymoassignment.enums.GithubIssueType;
import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.model.GithubRepoDetails;
import com.example.vymoassignment.repository.GithubIssueRepository;
import com.example.vymoassignment.util.Resource;

import java.util.List;

public class IssueListFragmentViewModel extends AndroidViewModel {

    private final GithubIssueRepository githubIssueRepository = new GithubIssueRepository(getApplication());

    public IssueListFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Resource<List<GithubIssue>>> getIssues(GithubRepoDetails githubRepoDetails,
                                                           GithubIssueType githubIssueType) {
        return githubIssueRepository.getIssues(githubRepoDetails, githubIssueType);
    }
}
