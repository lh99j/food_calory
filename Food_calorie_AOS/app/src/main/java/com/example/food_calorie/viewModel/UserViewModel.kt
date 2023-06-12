package com.example.food_calorie.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.food_calorie.network.data.request.LoginRequest
import com.example.food_calorie.network.data.request.SignUpRequest
import com.example.food_calorie.repository.UserRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy

class UserViewModel : ViewModel() {
    private val userRepository = UserRepositoryImpl()

    @SuppressLint("CheckResult")
    fun login(request: LoginRequest) {

        userRepository.login(request).subscribeBy(
            onSuccess = {

            },
            onError = {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    fun sendAuthCode(email: String) {

        userRepository.sendAuthCode(email).subscribeBy(
            onSuccess = {

            },
            onError = {
                it.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    fun signUp(request: SignUpRequest) {

        userRepository.signUp(request).subscribeBy(
            onSuccess = {

            },
            onError = {
                it.printStackTrace()
            })
    }
}