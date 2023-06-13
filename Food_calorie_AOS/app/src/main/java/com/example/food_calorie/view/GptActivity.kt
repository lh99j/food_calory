package com.example.food_calorie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.food_calorie.R
import com.example.food_calorie.databinding.ActivityGptBinding
import com.example.food_calorie.model.GptContent
import com.example.food_calorie.network.data.request.GptRequest
import com.example.food_calorie.viewModel.GptViewModel

class GptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGptBinding
    private val viewModel: GptViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGptBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.gptVm = viewModel


        binding.gptAnswerTv.movementMethod = ScrollingMovementMethod()

        val email = intent.getStringExtra("email")
        val totalCalorie = intent.getStringExtra("totalCalorie")
        Log.d("totalCalorielhj", "onCreate: $totalCalorie")

        sendChatGPT(totalCalorie!!)

        setSupportActionBar(binding.recommendToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.recommendToolbar.title = "$totalCalorie 칼로리"

        viewModel.gptText.observe(this, Observer {
            binding.gptAnswerTv.text = it
        })

        binding.gptGoHomeBtn.setOnClickListener {
            val intent = Intent(this@GptActivity, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }


    }


    fun sendChatGPT(text: String) {
        val model = "gpt-3.5-turbo"
        val request = GptRequest(
            model,
            listOf(
                GptContent(
                    "user",
                    "오늘 하루 ${text}칼로리를 먹었어. ${text}칼로리를 건강하게 먹는 식단 짜줘"
                )
            )
        )

        viewModel.getChatAnswer(request)
    }
}