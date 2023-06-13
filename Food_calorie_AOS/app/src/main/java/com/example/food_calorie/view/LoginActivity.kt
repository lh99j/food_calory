package com.example.food_calorie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.food_calorie.R
import com.example.food_calorie.databinding.ActivityLoginBinding
import com.example.food_calorie.network.data.request.LoginRequest
import com.example.food_calorie.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginVm = viewModel

        binding.loginLoginBtn.setOnClickListener {
            val email = binding.loginEmailEt.text.toString()
            val password = binding.loginPwEt.text.toString()
            viewModel.login(LoginRequest(email, password))
        }

        viewModel.loginResult.observe(this, Observer {
            if(it.resultCode == 0){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("email", binding.loginEmailEt.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, "이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.loginSignUpBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SingUpActivity::class.java)
            startActivity(intent)
        }
    }
}