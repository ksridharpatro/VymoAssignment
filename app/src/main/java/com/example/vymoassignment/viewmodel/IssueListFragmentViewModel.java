package com.example.vymoassignment.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.vymoassignment.enums.GithubIssueType;
import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.model.GithubRepoDetails;
import com.example.vymoassignment.repository.GithubIssueRepository;
import com.example.vymoassignment.util.Resource;

import java.util.List;

public class IssueListFragmentViewModel extends ViewModel {

    private GithubIssueRepository githubIssueRepository = new GithubIssueRepository();

    public IssueListFragmentViewModel(Context applicationContext) {

    }

    public LiveData<Resource<List<GithubIssue>>> getIssues(GithubRepoDetails githubRepoDetails,
                                                           GithubIssueType githubIssueType) {

        return githubIssueRepository.getIssues(githubRepoDetails, githubIssueType);
    }
}
