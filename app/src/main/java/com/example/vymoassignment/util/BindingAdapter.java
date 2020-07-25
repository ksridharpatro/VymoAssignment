package com.example.vymoassignment.util;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.vymoassignment.adapter.GithubIssueRecycleAdapter;
import com.example.vymoassignment.model.GithubIssue;

import java.util.List;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("goneUnless")
    public static void goneUnless(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @androidx.databinding.BindingAdapter({"issueList"})
    public static void setRecyclerAdapter(RecyclerView recyclerView, List<GithubIssue> issues) {
        GithubIssueRecycleAdapter adapter = (GithubIssueRecycleAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitIssues(issues);
        }
    }
}
