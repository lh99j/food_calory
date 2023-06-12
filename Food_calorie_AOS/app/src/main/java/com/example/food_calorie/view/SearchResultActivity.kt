package com.example.food_calorie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_calorie.R
import com.example.food_calorie.adapter.MainFoodDeleteRecyclerViewAdapter
import com.example.food_calorie.adapter.MainFoodRecyclerViewAdapter
import com.example.food_calorie.databinding.ActivitySearchResultBinding
import com.example.food_calorie.model.Food
import com.example.food_calorie.network.data.request.AddFoodRequest
import com.example.food_calorie.viewModel.FoodViewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private val viewModel: FoodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.searchVm = viewModel

        var calendarDate = ""
        if(intent.getStringExtra("calendarDate") != null){
            calendarDate = intent.getStringExtra("calendarDate")!!
        }

        setSupportActionBar(binding.foodToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.foodToolbar.title = calendarDate

        viewModel.getFoodList("", calendarDate)

        binding.mainFoodRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val mainRecyclerViewAdapter = MainFoodRecyclerViewAdapter()
        binding.mainFoodRv.adapter = mainRecyclerViewAdapter

        var list: MutableList<Food> = mutableListOf()

        binding.mainFoodAddIv.setOnClickListener {
            Log.d("countlhj", "1")
//            val foodName = binding.mainCalendarDateCl.text.toString()
//            viewModel.getFoodCalorie(foodName) { calorie ->
//                viewModel.addFoodDate(
//                    AddFoodRequest(
//                        foodName,
//                        calendarDate,
//                        calorie
//                    )
//                ) {
//                    // addFoodDate의 비동기 작업 완료 후에 실행되는 콜백
//                    viewModel.getFoodList(calendarDate)
//                }
//            }

            val foodName = binding.mainCalendarDateCl.text.toString()
            viewModel.getFoodListByKeyWord(foodName)
        }

//        viewModel.foodCaloire.observe(this, Observer {
//            Log.d("lhjet", "onCreate: observe ${binding.mainCalendarDateCl.text.toString()}")
//        })


        viewModel.foodListByKeyWord.observe(this, Observer {
            list.clear()
            Log.d("countlhj", "5")
            for (i in it.indices) {
                if (it[i].foodName != "" && it[i].calorie != "") {
                    list.add(Food(i + 1, it[i].foodName, it[i].calorie))
                }
            }

            mainRecyclerViewAdapter.submitSearchResultEventList(list)
        })

        mainRecyclerViewAdapter.submitSearchResultEventList(list)


        mainRecyclerViewAdapter.setOnItemClickListener(
            object:MainFoodRecyclerViewAdapter.OnItemClickListener{
                override fun onItemClick(v: View, data: Food, pos: Int) {
                    val intent = Intent(this@SearchResultActivity, MainActivity::class.java)
                    intent.putExtra("foodName", data.food)
                    intent.putExtra("date", calendarDate)
                    startActivity(intent)
                }
            }
        )
    }
}