package com.example.vymoassignment.network;

import com.example.vymoassignment.model.GithubIssue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubIssuesWebservice {
    @GET("repos/{orgName}/{repoName}/issues")
    Call<List<GithubIssue>> getGithubIssues(
            @Path("orgName") String orgName,
            @Path("repoName") String repoName,
            @Query("state") String state);
}
