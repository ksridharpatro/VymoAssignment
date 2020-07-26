package com.example.vymoassignment.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class GithubIssueReqEntity {
    @PrimaryKey
    @NonNull
    private final String repoId;
    private final long fetchTimestamp;

    public GithubIssueReqEntity(@NotNull String repoId, long fetchTimestamp) {
        this.repoId = repoId;
        this.fetchTimestamp = fetchTimestamp;
    }

    @NotNull
    public String getRepoId() {
        return repoId;
    }

    public long getFetchTimestamp() {
        return fetchTimestamp;
    }
}
