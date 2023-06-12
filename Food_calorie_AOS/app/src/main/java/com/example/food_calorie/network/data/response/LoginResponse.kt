package com.example.food_calorie.network.data.response

import com.example.food_calorie.model.LoginResult

data class LoginResponse(
    val resultCode: Int,
    val desc: String,
    val body: LoginResult
)