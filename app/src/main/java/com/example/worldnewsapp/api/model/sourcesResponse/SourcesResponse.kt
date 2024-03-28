package com.example.worldnewsapp.api.model.sourcesResponse

data class SourcesResponse(
    val sources: List<Source>,
    val status: String,
    val code: String,
    val message: String
)