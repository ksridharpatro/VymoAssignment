package com.example.vymoassignment.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.vymoassignment.model.GithubIssue;

@Database(entities = {GithubIssue.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GithubIssuesDao githubIssuesDao();
}
