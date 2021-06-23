package com.example.spaceflight.network

import com.example.spaceflight.models.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRepository {

    @GET("/v3/articles")
    suspend fun getAllArticles(): Response<Article>

    @GET("/v3/articles")
    suspend fun find(@Query("_contains") category: String): Response<Article>

}