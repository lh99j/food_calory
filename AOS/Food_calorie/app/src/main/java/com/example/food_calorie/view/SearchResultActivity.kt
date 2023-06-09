package com.example.food_calorie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_calorie.R
import com.example.food_calorie.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        var calendarDate = intent.getStringExtra("calendarDate")

        setSupportActionBar(binding.foodToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.foodToolbar.title = calendarDate
    }
}