package com.example.food_calorie.repository

import com.example.food_calorie.model.FoodData
import com.example.food_calorie.model.GetFoodData
import com.example.food_calorie.network.ApiClient
import com.example.food_calorie.network.data.request.AddFoodRequest
import com.example.food_calorie.network.data.request.LoginRequest
import com.example.food_calorie.network.data.request.SignUpRequest
import com.example.food_calorie.network.data.response.LoginResponse
import com.example.food_calorie.network.data.response.Response
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import retrofit2.http.Query

interface UserRepository {
    fun login(request: LoginRequest): Single<LoginResponse>
    fun sendAuthCode(email: String): Single<Response>
    fun signUp(request: SignUpRequest): Single<Response>
}


class UserRepositoryImpl: UserRepository{
    override fun login(request: LoginRequest): Single<LoginResponse> {
        return ApiClient.api.login(request)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

    override fun sendAuthCode(email: String): Single<Response> {
        return ApiClient.api.sendAuthCode(email)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }

    override fun signUp(request: SignUpRequest): Single<Response> {
        return ApiClient.api.signUp(request)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread()).map { it }
    }
}