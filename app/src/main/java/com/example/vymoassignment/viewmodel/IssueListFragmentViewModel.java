package com.example.vymoassignment.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.repository.GithubIssueRepository;
import com.example.vymoassignment.util.Resource;

import java.util.List;

public class IssueListFragmentViewModel extends ViewModel {

    private GithubIssueRepository githubIssueRepository = new GithubIssueRepository();

    public IssueListFragmentViewModel(Context applicationContext) {

    }

    public LiveData<Resource<List<GithubIssue>>> getIssues(
            String orgName,
            String repoName,
            String state) {

        return githubIssueRepository.getIssues(orgName, repoName, state);
    }
}
