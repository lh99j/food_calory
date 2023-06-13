package com.example.food_calorie.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_calorie.network.data.request.GptRequest
import com.example.food_calorie.repository.GptRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy

class GptViewModel : ViewModel(){
    private val gptRepository = GptRepositoryImpl()

    private var _gptText = MutableLiveData<String>()
    val gptText: LiveData<String>
        get() = _gptText

    @SuppressLint("CheckResult")
    fun getChatAnswer(request: GptRequest){
        gptRepository.getChatAnswer(request).subscribeBy (
            onSuccess = {
                _gptText.value = it.choices[0].message.content
                Log.d("lhjGpt", "getChatAnswer: $it")
            },
            onError = {
                it.printStackTrace()
            })
    }
}