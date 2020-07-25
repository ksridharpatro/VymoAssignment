package com.example.vymoassignment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.vymoassignment.R;
import com.example.vymoassignment.adapter.GithubIssueRecycleAdapter;
import com.example.vymoassignment.custom.VerticalSpaceItemDecoration;
import com.example.vymoassignment.databinding.FragmentIssueListBinding;
import com.example.vymoassignment.model.GithubIssue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IssueListFragment extends Fragment {

    private static final String ISSUE_STATUS = "issue_status";
    private String issueStatus;
    private FragmentIssueListBinding fragmentBinding;

    public IssueListFragment() {
        // Required empty public constructor
    }

    public static IssueListFragment newInstance(String issueStatus) {
        IssueListFragment fragment = new IssueListFragment();
        Bundle args = new Bundle();
        args.putString(ISSUE_STATUS, issueStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            issueStatus = getArguments().getString(ISSUE_STATUS);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_issue_list, container, false);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        fragmentBinding.issueListRecycler.addItemDecoration(
                new VerticalSpaceItemDecoration(20));
        fragmentBinding.issueListRecycler.setAdapter(new GithubIssueRecycleAdapter());
        fragmentBinding.setIssues(getDummyData());
    }

    public List<GithubIssue> getDummyData() {
        List<GithubIssue> issues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GithubIssue githubIssue = new GithubIssue();
            githubIssue.setPullTitle("[WIP] loom and jdk 15 testing");
            githubIssue.setPrNumber("#78779799");
            githubIssue.setPullStatus("Open");
            githubIssue.setCreated("Today");
            issues.add(githubIssue);
        }
        return issues;
    }
}