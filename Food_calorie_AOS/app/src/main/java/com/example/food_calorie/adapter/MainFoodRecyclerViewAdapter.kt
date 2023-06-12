package com.example.food_calorie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_calorie.R
import com.example.food_calorie.model.Food

class MainFoodRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var foodData: List<Food>? = null
    private var count = 1

    interface OnItemClickListener{
        fun onItemClick(v: View, data: Food, pos: Int)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.food_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        foodData?.let { foodData ->
            (holder as SearchResultViewHolder).bind(foodData[position])
        }
    }

    override fun getItemCount(): Int {
        return foodData!!.size
    }

    fun submitSearchResultEventList(list: List<Food>) {
        foodData = list
        notifyDataSetChanged()
    }

    inner class SearchResultViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.food_id_tv)
        val food: TextView = itemView.findViewById(R.id.food_name_tv)
        val calorie: TextView = itemView.findViewById(R.id.food_calorie_tv)

        //        val img : ImageView = itemView.findViewById(R.id.item_event_iv)
        fun bind(foodData: Food) {
            id.text = foodData.id.toString()
            food.text = foodData.food
            calorie.text = foodData.calorie
//            img.setImageDrawable(communityEvent.img)

            val pos = adapterPosition

            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, foodData, pos)
                }
            }

        }
    }
}