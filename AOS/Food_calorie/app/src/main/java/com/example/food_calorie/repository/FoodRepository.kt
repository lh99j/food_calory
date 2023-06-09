package com.example.food_calorie.repository

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.network.ApiClient
import com.example.food_calorie.network.data.request.AddFoodRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import retrofit2.http.Query


interface FoodRepository {
    fun getFoodList(date: String): Single<List<FoodData>>
    fun getFoodCalorie(foodName:String): Single<String>
    fun addFoodDate(request: AddFoodRequest):Single<String>
}


class FoodRepositoryImpl: FoodRepository{
    override fun getFoodList(date: String): Single<List<FoodData>> {
        return ApiClient.api.getFoodList(date)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

    override fun getFoodCalorie(foodName: String): Single<String> {
        return ApiClient.api.getFoodCalorie(foodName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

    override fun addFoodDate(request: AddFoodRequest): Single<String> {
        return ApiClient.api.addFoodDate(request)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }
}