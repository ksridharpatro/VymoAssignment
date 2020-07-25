package com.example.vymoassignment.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private static final String DATABASE_NAME = "github_issues_db";
    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabaseInstance(Context applicationContext) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(applicationContext, AppDatabase.class, DATABASE_NAME).build();
        }
        return appDatabase;
    }
}
