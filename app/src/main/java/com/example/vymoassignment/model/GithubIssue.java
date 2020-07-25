package com.example.vymoassignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class GithubIssue {
    @PrimaryKey
    @SerializedName("id")
    private long issueId;
    @SerializedName("title")
    private String pullTitle;
    @SerializedName("number")
    private String prNumber;
    @SerializedName("state")
    private String pullStatus;
    @SerializedName("created_at")
    private String created;
    private String repoId;

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getPullTitle() {
        return pullTitle;
    }

    public void setPullTitle(String pullTitle) {
        this.pullTitle = pullTitle;
    }

    public String getPrNumber() {
        return prNumber;
    }

    public void setPrNumber(String prNumber) {
        this.prNumber = prNumber;
    }

    public String getPullStatus() {
        return pullStatus;
    }

    public void setPullStatus(String pullStatus) {
        this.pullStatus = pullStatus;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
