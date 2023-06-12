package com.example.food_calorie.network.data.request

data class AddFoodRequest(
    val email: String,
    val foodName: String,
    val date: String,
    val calorie: String
)