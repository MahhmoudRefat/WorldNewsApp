package com.example.worldnewsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiManager {

    private  var retrofit: Retrofit

    init {
        //custom logger with lambda expression
        val logging = HttpLoggingInterceptor { message ->
            Log.e("Api -> ", message)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder().client(client).baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    fun getServices(): webServices {
        return retrofit.create(webServices::class.java)
    }
}