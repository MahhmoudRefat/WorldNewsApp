package com.example.worldnewsapp.api

import com.example.worldnewsapp.api.model.sourcesResponse.Source
import com.example.worldnewsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface webServices {

    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey:String = Constants.apiKey
    ):Call<SourcesResponse>
}