package com.example.food_calory.Repository;

import com.example.food_calory.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByFoodName(String foodName);
    List<Food> findByFoodNameContaining(String foodName);
}
