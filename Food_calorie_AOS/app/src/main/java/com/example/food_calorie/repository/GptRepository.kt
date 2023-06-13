package com.example.food_calorie.repository

import com.example.food_calorie.network.chatGPT.GptApiClient
import com.example.food_calorie.network.data.request.GptRequest
import com.example.food_calorie.network.data.response.GptResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface GptRepository {
    fun getChatAnswer(request: GptRequest): Single<GptResponse>
}

class GptRepositoryImpl: GptRepository{
    override fun getChatAnswer(request: GptRequest): Single<GptResponse> {
        return GptApiClient.api.getChatAnswer(request)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

}