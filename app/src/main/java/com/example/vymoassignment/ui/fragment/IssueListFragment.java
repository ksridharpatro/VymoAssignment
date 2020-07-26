package com.example.vymoassignment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vymoassignment.R;
import com.example.vymoassignment.adapter.GithubIssueRecycleAdapter;
import com.example.vymoassignment.custom.VerticalSpaceItemDecoration;
import com.example.vymoassignment.databinding.FragmentIssueListBinding;
import com.example.vymoassignment.enums.GithubIssueType;
import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.model.GithubRepoDetails;
import com.example.vymoassignment.util.Resource;
import com.example.vymoassignment.viewmodel.IssueListFragmentViewModel;
import com.example.vymoassignment.viewmodel.IssueListFragmentViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class IssueListFragment extends Fragment {

    private static final String ISSUE_TYPE = "issue_status";
    private static final String GITHUB_REPO_DETAILS = "github_repo_details";
    private FragmentIssueListBinding fragmentBinding;
    private IssueListFragmentViewModel fragmentViewModel;
    private GithubRepoDetails githubRepoDetails;
    private GithubIssueType githubIssueType;

    public IssueListFragment() {
        // Required empty public constructor
    }

    public static IssueListFragment newInstance(GithubRepoDetails githubRepoDetails,
                                                GithubIssueType githubIssueType) {
        IssueListFragment fragment = new IssueListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ISSUE_TYPE, githubIssueType);
        args.putParcelable(GITHUB_REPO_DETAILS, githubRepoDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            githubIssueType = (GithubIssueType) getArguments().getSerializable(ISSUE_TYPE);
            githubRepoDetails = getArguments().getParcelable(GITHUB_REPO_DETAILS);
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
        fragmentViewModel = new ViewModelProvider(this,
                new IssueListFragmentViewModelFactory(Objects.requireNonNull(getActivity()).getApplication()))
                .get(IssueListFragmentViewModel.class);
        setupRecyclerView();
        updateUiState();
    }

    private void setupRecyclerView() {
        fragmentBinding.issueListRecycler.addItemDecoration(
                new VerticalSpaceItemDecoration(20));
        fragmentBinding.issueListRecycler.setAdapter(new GithubIssueRecycleAdapter());
    }

    private void updateUiState() {
        fragmentViewModel.getIssues(githubRepoDetails, githubIssueType).observe(
                this,
                viewState -> {
                    if (viewState instanceof Resource.Loading) {
                        fragmentBinding.setShowProgress(true);
                    } else if (viewState instanceof Resource.Error) {
                        fragmentBinding.setShowProgress(false);
                        String errorMessage = ((Resource.Error<List<GithubIssue>>) viewState).getMessage();
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    } else if (viewState instanceof Resource.Success) {
                        fragmentBinding.setShowProgress(false);
                        fragmentBinding.setIssues(((Resource.Success<List<GithubIssue>>) viewState).getData());
                    }
                });
    }
}