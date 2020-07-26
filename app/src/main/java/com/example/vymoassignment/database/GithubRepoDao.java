package com.example.vymoassignment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GithubRepoDao {
    @Query("SELECT * FROM GithubIssueReqEntity WHERE repoId = :repoId")
    GithubIssueReqEntity getRepoById(String repoId);

    @Insert
    void insert(GithubIssueReqEntity githubIssueReqEntity);

    @Query("DELETE FROM GithubIssueReqEntity WHERE repoId = :repoId")
    void deleteByRepoId(String repoId);
}
