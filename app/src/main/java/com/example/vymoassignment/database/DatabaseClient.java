package com.example.vymoassignment.database;

import android.app.Application;

import androidx.room.Room;

public class DatabaseClient {
    private static final String DATABASE_NAME = "github_issues_db";
    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabaseInstance(Application application) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(application, AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}
