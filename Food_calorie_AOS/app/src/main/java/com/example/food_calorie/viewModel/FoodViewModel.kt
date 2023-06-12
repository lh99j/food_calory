package com.example.food_calorie.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_calorie.model.FoodData
import com.example.food_calorie.model.GetFoodData
import com.example.food_calorie.network.data.request.AddFoodRequest
import com.example.food_calorie.repository.FoodRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy
import kotlin.math.log

class FoodViewModel : ViewModel() {
    private val foodRepository = FoodRepositoryImpl()

    private var _foodData = MutableLiveData<List<FoodData>>()
    val foodData: LiveData<List<FoodData>>
        get() = _foodData

    private var _foodCalorie = MutableLiveData<String>()
    val foodCaloire: LiveData<String>
        get() = _foodCalorie

    private var _foodListByKeyWord = MutableLiveData<List<GetFoodData>>()
    val foodListByKeyWord: LiveData<List<GetFoodData>>
        get() = _foodListByKeyWord


    init {
        _foodCalorie.value = ""
    }

    @SuppressLint("CheckResult")
    fun getFoodList(date: String){

        foodRepository.getFoodList(date).subscribeBy (
            onSuccess = {
                Log.d("countlhj", "4")
                _foodData.value = it
                Log.d("lhj", "findAllPerfume: $it")
            },
            onError = {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    fun getFoodCalorie(foodName: String, callback: (String) -> Unit) {
        foodRepository.getFoodCalorie(foodName).subscribeBy (
            onSuccess = {
                Log.d("countlhj", "2")
                _foodCalorie.value = it
                Log.d("lhjet", "findAllPerfume: $it")
                callback(it) // 작업이 완료되면 콜백 호출
            },
            onError = {
                it.printStackTrace()
            })
    }


    @SuppressLint("CheckResult")
    fun addFoodDate(request: AddFoodRequest, callback: () -> Unit) {
        foodRepository.addFoodDate(request).subscribeBy (
            onSuccess = {
                Log.d("countlhj", "3")
                Log.d("lhj", "findAllPerfume: $it")
                callback() // 작업이 완료되면 콜백 호출
            },
            onError = {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    fun deleteFoodData(date: String, foodName: String, callback: (String) -> Unit) {
        foodRepository.deleteFoodData(date, foodName).subscribeBy (
            onSuccess = {
                Log.d("lhj", "deleteFoodData: $it")
                callback(it) // 작업이 완료되면 콜백 호출
            },
            onError = {
                it.printStackTrace()
            })
    }


    @SuppressLint("CheckResult")
    fun getFoodListByKeyWord(foodName: String){
        foodRepository.getFoodListByKeyWord(foodName).subscribeBy (
            onSuccess = {
                _foodListByKeyWord.value = it
            },
            onError = {
                it.printStackTrace()
            })
    }
}