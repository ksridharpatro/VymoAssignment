package com.example.vymoassignment.model;

public class GithubIssue {
    private String pullTitle;
    private String prNumber;
    private String pullStatus;
    private String created;

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
