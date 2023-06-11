package com.example.food_calory.Repository;

import com.example.food_calory.model.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {
    List<FoodIntake> findByDate(String date);

    List<FoodIntake> findAllByDateAndFoodName(String date, String foodName);
}