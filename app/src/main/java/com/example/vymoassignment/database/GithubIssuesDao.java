package com.example.vymoassignment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vymoassignment.model.GithubIssue;

import java.util.List;

@Dao
public interface GithubIssuesDao {
    @Query("SELECT * FROM GithubIssue WHERE repoId = :repoId")
    List<GithubIssue> findAllByRepoId(String repoId);

    @Insert
    void insertAll(List<GithubIssue> issues);

    @Query("DELETE FROM githubissue")
    void deleteAll();

    @Query("DELETE FROM githubissue WHERE repoId = :repoId")
    void deleteByRepoId(String repoId);
}
