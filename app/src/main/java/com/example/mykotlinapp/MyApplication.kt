package com.example.mykotlinapp

import android.app.Application
import com.example.mykotlinapp.apiservice.NewsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    lateinit var newsApiService: NewsApiService
    private lateinit var retrofit: Retrofit
    val BaseURL="https://newsapi.org/v2/"

    override fun onCreate() {
        super.onCreate()

        initializeRetrofit()
        getNewAPI()
    }

    private fun initializeRetrofit(){

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
    private fun getNewAPI(){
        newsApiService = retrofit.create(NewsApiService::class.java)
    }
}