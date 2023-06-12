package com.example.food_calorie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.food_calorie.R
import com.example.food_calorie.databinding.ActivitySingUpBinding
import com.example.food_calorie.network.data.request.SignUpRequest
import com.example.food_calorie.viewModel.UserViewModel

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySingUpBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signupVm = viewModel

        binding.loginSignUpBtn.setOnClickListener {
            val email = binding.loginEmailEt.text.toString()
            val password = binding.loginPwEt.text.toString()
            val authCode = binding.loginAuthEt.text.toString()
            viewModel.signUp(SignUpRequest(email, password, authCode))
        }

        binding.signupAuthBtn.setOnClickListener {
            val email = binding.loginEmailEt.text.toString()
            viewModel.sendAuthCode(email)
        }

        viewModel.signUpResult.observe(this, Observer {
            if(it.resultCode == 0){
                Toast.makeText(this, it.desc, Toast.LENGTH_SHORT)

                val intent = Intent(this@SingUpActivity, LoginActivity::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, it.desc, Toast.LENGTH_SHORT)
            }
        })
    }
}