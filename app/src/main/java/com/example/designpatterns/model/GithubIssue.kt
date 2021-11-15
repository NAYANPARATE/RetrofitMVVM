package com.example.designpatterns.model

import com.google.gson.annotations.SerializedName

data class GithubIssue(
    @SerializedName("id")
    val id: Int ,

    @SerializedName("title")
    val title: String ,

    @SerializedName("created_at")
    val date: String)
