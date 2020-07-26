package com.example.vymoassignment.enums;

public enum GithubIssueType {
    OPEN("open"), CLOSED("closed");
    private final String typeString;

    GithubIssueType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }
}
