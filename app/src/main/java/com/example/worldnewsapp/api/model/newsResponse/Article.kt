package com.example.worldnewsapp.api.model.newsResponse

import com.example.worldnewsapp.api.model.sourcesResponse.Source

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)