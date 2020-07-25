package com.example.vymoassignment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vymoassignment.model.GithubIssue;

import java.util.List;

@Dao
public interface GithubIssuesDao {
    @Query("SELECT * FROM GithubIssue")
    List<GithubIssue> getAll();

    @Insert
    void insertAll(GithubIssue... issues);

    @Query("DELETE FROM githubissue;")
    void deleteAll();
}
