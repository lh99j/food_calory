package com.example.food_calorie.repository

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.model.GetFoodData
import com.example.food_calorie.network.ApiClient
import com.example.food_calorie.network.data.request.AddFoodRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import retrofit2.http.Query


interface FoodRepository {
    fun getFoodList(email: String, date: String): Single<List<FoodData>>
    fun getFoodCalorie(foodName:String): Single<String>
    fun addFoodDate(request: AddFoodRequest):Single<String>
    fun deleteFoodData(email: String, date: String, foodName: String): Single<String>
    fun getFoodListByKeyWord(foodName:String): Single<List<GetFoodData>>
}


class FoodRepositoryImpl: FoodRepository{
    override fun getFoodList(email: String, date: String): Single<List<FoodData>> {
        return ApiClient.api.getFoodList(email, date)
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

    override fun deleteFoodData(email: String, date: String, foodName: String): Single<String> {
        return ApiClient.api.deleteFoodData(email, date, foodName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

    override fun getFoodListByKeyWord(foodName: String): Single<List<GetFoodData>> {
        return ApiClient.api.getFoodListByKeyWord(foodName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }
}