package com.example.food_calory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDao extends JpaRepository<Food, Long> {
    Food findByFoodName(String foodName);
}
