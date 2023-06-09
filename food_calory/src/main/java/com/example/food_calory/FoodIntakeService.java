package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodIntakeService {
    private final FoodIntakeRepository foodIntakeRepository;

    public void addFoodIntake(FoodIntake foodIntake) {
        foodIntakeRepository.save(foodIntake);
    }

    public List<FoodIntake> getFoodIntakeListByDate(String date) {
        return foodIntakeRepository.findByDate(date);
    }

    public void deleteFoodIntake(Long id) {
        foodIntakeRepository.deleteById(id);
    }
}