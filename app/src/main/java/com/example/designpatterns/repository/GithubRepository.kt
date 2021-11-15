package com.example.designpatterns.repository

import androidx.lifecycle.LiveData
import com.example.designpatterns.model.GithubIssue
import com.example.designpatterns.network.RetrofitClient
import retrofit2.Response

class GithubRepository {

    suspend fun getIssues() : Response<List<GithubIssue>> {
        return RetrofitClient.retrofit.getIssues()
    }
}