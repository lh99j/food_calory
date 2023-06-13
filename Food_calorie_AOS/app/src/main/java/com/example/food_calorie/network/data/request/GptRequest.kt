package com.example.food_calorie.network.data.request

import com.example.food_calorie.model.GptContent

data class GptRequest(
    val model: String,
    val messages: List<GptContent>
)