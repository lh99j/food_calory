package com.example.food_calorie.model

import com.google.gson.annotations.SerializedName

data class GptChoices(
    @SerializedName("message")
    val message: GptMessage
)