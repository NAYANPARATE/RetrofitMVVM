package com.example.designpatterns.network

import androidx.lifecycle.LiveData
import com.example.designpatterns.model.GithubIssue
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiInterface {

    @GET("repos/octocat/hello-world/issues")
    suspend fun getIssues() : Response<List<GithubIssue>>

}