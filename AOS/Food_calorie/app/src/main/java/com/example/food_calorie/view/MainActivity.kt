package com.example.food_calorie.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_calorie.R
import com.example.food_calorie.adapter.MainFoodRecyclerViewAdapter
import com.example.food_calorie.databinding.ActivityMainBinding
import com.example.food_calorie.model.Food
import com.example.food_calorie.viewModel.FoodViewModel
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: FoodViewModel by viewModels()
    private var calendarDate = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.mainVm = viewModel

        binding.mainFoodRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val mainRecyclerViewAdapter = MainFoodRecyclerViewAdapter()
        binding.mainFoodRv.adapter = mainRecyclerViewAdapter

        var list: MutableList<Food> = mutableListOf()


        binding.mainCalendarDateCl.setOnClickListener {
            showStartTimePicker(this)
        }

        viewModel.foodData.observe(this, Observer {
            Log.d("lhj", "onCreate: ${it}")

            list.clear()

            for(i in it.indices){
                list.add(Food(it[i].foodName, it[i].calorie))
            }

            mainRecyclerViewAdapter.submitSearchResultEventList(list)
        })

        mainRecyclerViewAdapter.submitSearchResultEventList(list)

        binding.mainFoodAddIv.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
            intent.putExtra("calendarDate", calendarDate)
            startActivity(intent)
        }


    }


    private fun showStartTimePicker(context: Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)

        var selectedTime: String = ""

        val listener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
            var m = (month + 1).toString()
            var d = date.toString()

            if(m.length == 1){
                m = "0$m"
            }

            if(d.length == 1){
                d = "0$d"
            }

            selectedTime += "$year-$m-$d"

            binding.mainCalendarDateTv.text = selectedTime
            viewModel.getFoodList(selectedTime)
            calendarDate = selectedTime
        }


        val picker = DatePickerDialog(context, listener, year, month, date)
        picker.setTitle("날짜 선택")
        picker.show()

    }

}