package com.example.food_calorie.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_calorie.model.FoodData
import com.example.food_calorie.repository.FoodRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy

class FoodViewModel : ViewModel() {
    private val foodRepository = FoodRepositoryImpl()

    private var _foodData = MutableLiveData<List<FoodData>>()
    val foodData: LiveData<List<FoodData>>
        get() = _foodData

    @SuppressLint("CheckResult")
    fun getFoodList(date: String){
        foodRepository.getFoodList(date).subscribeBy (
            onSuccess = {
                _foodData.value = it
                Log.d("lhj", "findAllPerfume: $it")
            },
            onError = {
                it.printStackTrace()
            })
    }
}