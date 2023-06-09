package com.example.food_calorie.network

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.network.data.request.AddFoodRequest
import io.reactivex.Single
import retrofit2.http.*

interface  ApiInterface {
    //ChatGPT POST
//    @POST("chat/completions")
//    fun getChatAnswer(@Body request: GptRequest): Single<GptResponse>
//
//    // perfume find all
//    @GET("/api/perfume/findAll")
//    fun findAllPerfumes(): Single<List<FindAllPerfumeResponse>>
//
//    @GET("/api/perfume/get_perfumes")
//    fun findPerfumeByName(@Query("name") name: String): Single<PerfumeResponse>

    @GET("/food-intake/list")
    fun getFoodList(@Query("date") date: String): Single<List<FoodData>>

    @GET("/food")
    fun getFoodCalorie(@Query("foodName") foodName:String): Single<String>

    @POST("/food-intake/create")
    fun addFoodDate(@Body request: AddFoodRequest):Single<String>
}
