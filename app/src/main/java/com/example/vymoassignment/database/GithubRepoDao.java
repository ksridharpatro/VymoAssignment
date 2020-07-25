package com.example.vymoassignment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GithubRepoDao {
    @Query("SELECT * FROM GITHUBREPODETAILSCACHEENTITY WHERE repoId = :repoId")
    GithubRepoDetailsCacheEntity getRepoById(String repoId);

    @Insert
    void insert(GithubRepoDetailsCacheEntity githubRepoDetailsCacheEntity);
}
