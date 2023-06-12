package com.example.food_calory.Service;

import com.example.food_calory.model.FoodIntake;
import com.example.food_calory.Repository.FoodIntakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodIntakeService {
    private final FoodIntakeRepository foodIntakeRepository;

    public void addFoodIntake(FoodIntake foodIntake) {
        foodIntakeRepository.save(foodIntake);
    }

    public List<FoodIntake> getFoodIntakeListByEmailAndDate(String email, String date) {
        return foodIntakeRepository.findByEmailAndDate(email, date);
    }

    public void deleteDuplicateFoodIntakeByEmailAndDateAndFoodName(String email, String date, String foodName) {
        List<FoodIntake> foodIntakeList = foodIntakeRepository.findAllByEmailAndDateAndFoodName(email, date, foodName);
        if (foodIntakeList.size() > 1) {
            // 중복된 기록 중 하나만 삭제
            FoodIntake duplicateFoodIntake = foodIntakeList.get(0);
            foodIntakeRepository.delete(duplicateFoodIntake);
        }else{
            foodIntakeRepository.delete(foodIntakeList.get(0));
        }
    }
}