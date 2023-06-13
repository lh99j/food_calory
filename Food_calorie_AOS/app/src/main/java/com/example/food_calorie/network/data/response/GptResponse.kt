package com.example.food_calorie.network.data.response

import com.example.food_calorie.model.GptChoices
import com.google.gson.annotations.SerializedName

data class GptResponse(
    @SerializedName("choices")
    val choices: List<GptChoices>
)