package com.example.mykotlinapp.apiservice

import com.example.mykotlinapp.model.dto.NewsResponceDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {

    @GET("everything")
    fun getNews(
        @Query("q") query:String,
        @Query("from") fromDate:String,
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey:String,
    ): Single<Response<NewsResponceDTO>>
}