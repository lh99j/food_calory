package com.example.food_calorie.repository

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.network.ApiClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Query


interface FoodRepository {
    fun getFoodList(date: String): Single<List<FoodData>>
}


class FoodRepositoryImpl: FoodRepository{
    override fun getFoodList(date: String): Single<List<FoodData>> {
        return ApiClient.api.getFoodList(date)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

}