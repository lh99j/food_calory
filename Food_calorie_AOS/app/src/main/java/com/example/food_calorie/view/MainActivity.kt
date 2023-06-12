package com.example.food_calorie.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_calorie.R
import com.example.food_calorie.adapter.MainFoodDeleteRecyclerViewAdapter
import com.example.food_calorie.adapter.MainFoodRecyclerViewAdapter
import com.example.food_calorie.databinding.ActivityMainBinding
import com.example.food_calorie.model.Food
import com.example.food_calorie.network.data.request.AddFoodRequest
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

        binding.mainFoodRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val mainRecyclerViewAdapter = MainFoodDeleteRecyclerViewAdapter()
        binding.mainFoodRv.adapter = mainRecyclerViewAdapter

        var list: MutableList<Food> = mutableListOf()


        binding.mainCalendarDateCl.setOnClickListener {
            showStartTimePicker(this)
        }

        viewModel.foodData.observe(this, Observer {
            Log.d("lhj", "onCreate: ${it}")

            list.clear()

            for (i in it.indices) {
                list.add(Food((i + 1), it[i].foodName, it[i].calorie))
            }

            mainRecyclerViewAdapter.submitSearchResultEventList(list)
        })

        mainRecyclerViewAdapter.submitSearchResultEventList(list)

        binding.mainFoodAddIv.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
            if (calendarDate == "") {
                intent.putExtra("calendarDate", binding.mainCalendarDateTv.text)
            } else {
                intent.putExtra("calendarDate", calendarDate)
            }
            startActivity(intent)
        }

        mainRecyclerViewAdapter.setOnItemClickListener(
            object : MainFoodDeleteRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(v: View, data: Food, pos: Int) {
                    if(calendarDate == ""){
                        viewModel.deleteFoodData("", binding.mainCalendarDateTv.text.toString(), data.food) {
                            viewModel.getFoodList("", binding.mainCalendarDateTv.text.toString())
                        }
                    }else{
                        viewModel.deleteFoodData("", calendarDate, data.food) {
                            viewModel.getFoodList("", calendarDate)
                        }
                    }

                }
            }
        )

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

            if (m.length == 1) {
                m = "0$m"
            }

            if (d.length == 1) {
                d = "0$d"
            }

            selectedTime += "$year-$m-$d"

            binding.mainCalendarDateTv.text = selectedTime
            viewModel.getFoodList("", selectedTime)
            calendarDate = selectedTime
        }


        val picker = DatePickerDialog(context, listener, year, month, date)
        picker.setTitle("날짜 선택")
        picker.show()

    }

    override fun onResume() {
        super.onResume()

        if (intent.getStringExtra("foodName") != null) {
            var foodName = intent.getStringExtra("foodName")
            var date = intent.getStringExtra("date")
            viewModel.getFoodCalorie(foodName!!) { calorie ->
                viewModel.addFoodDate(
                    AddFoodRequest(
                        "",
                        foodName,
                        date!!,
                        calorie
                    )
                ) {
                    // addFoodDate의 비동기 작업 완료 후에 실행되는 콜백
                    viewModel.getFoodList("", date)
                }
            }

            binding.mainCalendarDateTv.text = date
        }
    }

}