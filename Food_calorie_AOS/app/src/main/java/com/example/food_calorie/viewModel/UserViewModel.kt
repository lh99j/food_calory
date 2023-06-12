package com.example.food_calorie.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_calorie.network.data.request.LoginRequest
import com.example.food_calorie.network.data.request.SignUpRequest
import com.example.food_calorie.network.data.response.LoginResponse
import com.example.food_calorie.network.data.response.Response
import com.example.food_calorie.repository.UserRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy

class UserViewModel : ViewModel() {
    private val userRepository = UserRepositoryImpl()

    private var _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse>
        get() = _loginResult

    private var _signUpResult = MutableLiveData<Response>()
    val signUpResult: LiveData<Response>
        get() = _signUpResult

    @SuppressLint("CheckResult")
    fun login(request: LoginRequest) {

        userRepository.login(request).subscribeBy(
            onSuccess = {
                _loginResult.value = it
                Log.d("lhjlogin", "login: $it")
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
                _signUpResult.value = it
                Log.d("lhjsign", "signUp: $it")
            },
            onError = {
                it.printStackTrace()
            })
    }
}