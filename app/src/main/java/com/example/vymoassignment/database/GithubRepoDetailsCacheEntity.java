package com.example.vymoassignment.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GithubRepoDetailsCacheEntity {
    @PrimaryKey
    private String repoId;
    private String fetchTimestamp;
}
