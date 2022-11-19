package com.example.myapplication.network

import com.example.myapplication.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY ="af1d86fffb1b48f1b35c1621ccca8108"

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getNews(@Query("q") q:String,@Query("apiKey")apiKey:String= API_KEY):Response<News>
}