package com.example.worldnewsapp.api.model.newsResponse

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
    val code: String,
    val message: String
)