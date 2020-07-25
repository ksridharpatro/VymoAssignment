package com.example.vymoassignment.enums;

public enum GithubIssueType {
    OPEN("open"), CLOSED("closed");
    private String typeString;

    GithubIssueType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }
}
