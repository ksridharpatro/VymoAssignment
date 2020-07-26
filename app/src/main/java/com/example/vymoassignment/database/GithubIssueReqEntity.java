package com.example.vymoassignment.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class GithubIssueReqEntity {
    @PrimaryKey
    @NonNull
    private String repoId;
    private long fetchTimestamp;

    public GithubIssueReqEntity(@NotNull String repoId, long fetchTimestamp) {
        this.repoId = repoId;
        this.fetchTimestamp = fetchTimestamp;
    }

    @NotNull
    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(@NotNull String repoId) {
        this.repoId = repoId;
    }

    public long getFetchTimestamp() {
        return fetchTimestamp;
    }

    public void setFetchTimestamp(long fetchTimestamp) {
        this.fetchTimestamp = fetchTimestamp;
    }
}
