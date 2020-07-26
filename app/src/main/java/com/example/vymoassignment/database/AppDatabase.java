package com.example.vymoassignment.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.vymoassignment.model.GithubIssue;

@Database(entities = {GithubIssue.class, GithubIssueReqEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GithubIssuesDao githubIssuesDao();

    public abstract GithubRepoDao githubRepoDao();
}
