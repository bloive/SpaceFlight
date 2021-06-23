package com.example.spaceflight.models

import com.google.gson.annotations.SerializedName

class Article : ArrayList<ArticleItem>()

data class ArticleItem(
    @SerializedName("title")
    val title: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("events")
    val events: List<Event>?
)

data class Event(
    @SerializedName("provider")
    val provider: String?
)
