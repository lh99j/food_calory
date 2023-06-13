package com.example.food_calorie.network.chatGPT

import com.example.food_calorie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class GptApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", "application/json")
        builder.addHeader("Authorization", "Bearer ${BuildConfig.CHATGPT_API_KEY}")

        return chain.proceed(builder.build())
    }
}