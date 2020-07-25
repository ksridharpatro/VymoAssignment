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
import com.example.vymoassignment.model.GithubIssue;
import com.example.vymoassignment.util.Resource;
import com.example.vymoassignment.viewmodel.IssueListFragmentViewModel;
import com.example.vymoassignment.viewmodel.IssueListFragmentViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class IssueListFragment extends Fragment {

    private static final String ISSUE_STATUS = "issue_status";
    private String issueStatus;
    private FragmentIssueListBinding fragmentBinding;
    private IssueListFragmentViewModel fragmentViewModel;

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
        fragmentViewModel = new ViewModelProvider(
                requireActivity(),
                new IssueListFragmentViewModelFactory(
                        Objects.requireNonNull(getContext()).getApplicationContext()))
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
        fragmentViewModel.getIssues("prestodb", "presto", issueStatus).observe(
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