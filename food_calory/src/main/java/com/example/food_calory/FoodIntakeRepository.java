package com.example.food_calory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {
    List<FoodIntake> findByDate(String date);
}