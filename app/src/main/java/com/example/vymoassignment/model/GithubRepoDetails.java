package com.example.vymoassignment.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class GithubRepoDetails extends BaseObservable {
    private String ownerName;
    private String repoName;

    @Bindable
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        notifyPropertyChanged(BR.ownerName);
    }

    @Bindable
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
        notifyPropertyChanged(BR.repoName);
    }
}
