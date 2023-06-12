package com.example.food_calorie.network.data.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val authCode: String
)