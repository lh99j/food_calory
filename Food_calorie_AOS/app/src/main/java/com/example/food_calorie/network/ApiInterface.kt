package com.example.food_calorie.network

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.model.GetFoodData
import com.example.food_calorie.network.data.request.AddFoodRequest
import com.example.food_calorie.network.data.request.LoginRequest
import com.example.food_calorie.network.data.request.SignUpRequest
import com.example.food_calorie.network.data.response.LoginResponse
import com.example.food_calorie.network.data.response.Response
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
    fun getFoodList(@Query("email") email: String, @Query("date") date: String): Single<List<FoodData>>

    @GET("/food")
    fun getFoodCalorie(@Query("foodName") foodName:String): Single<String>

    @POST("/food-intake/create")
    fun addFoodDate(@Body request: AddFoodRequest):Single<String>

    @DELETE("/food-intake/delete")
    fun deleteFoodData(@Query("email") email: String, @Query("date") date: String, @Query("foodName") foodName: String): Single<String>

    @GET("/food/foodList")
    fun getFoodListByKeyWord(@Query("foodName") foodName:String): Single<List<GetFoodData>>

    @POST("/api/login")
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @POST("/signUp/email/sendCode")
    fun sendAuthCode(@Query("email") email: String): Single<Response>

    @POST("/signUp/email/verify")
    fun signUp(@Body request: SignUpRequest): Single<Response>
}
