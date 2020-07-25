package com.example.vymoassignment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vymoassignment.R;
import com.example.vymoassignment.databinding.RowSingleIssueBinding;
import com.example.vymoassignment.model.GithubIssue;

import java.util.List;

public class GithubIssueRecycleAdapter extends
        RecyclerView.Adapter<GithubIssueRecycleAdapter.ViewHolder> {

    private List<GithubIssue> issues;

    @NonNull
    @Override
    public GithubIssueRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
        RowSingleIssueBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_single_issue, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubIssueRecycleAdapter.ViewHolder holder, int position) {
        holder.bind(issues.get(position));
    }

    @Override
    public int getItemCount() {
        return issues == null ? 0 : issues.size();
    }

    public void submitIssues(List<GithubIssue> issues) {
        this.issues = issues;
        notifyDataSetChanged();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        RowSingleIssueBinding itemBinding;

        public ViewHolder(RowSingleIssueBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        private void bind(GithubIssue githubIssue) {
            itemBinding.setGithubIssue(githubIssue);
        }
    }
}
