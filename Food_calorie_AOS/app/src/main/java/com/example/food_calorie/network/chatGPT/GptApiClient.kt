package com.example.food_calorie.network.chatGPT

import com.example.food_calorie.network.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GptApiClient {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(GptApiInterceptor())
        connectTimeout(1000, TimeUnit.SECONDS)
        readTimeout(1000, TimeUnit.SECONDS)
        writeTimeout(1000, TimeUnit.SECONDS)
    }.build()
    var gson = GsonBuilder().setLenient().create()
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.openai.com/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}