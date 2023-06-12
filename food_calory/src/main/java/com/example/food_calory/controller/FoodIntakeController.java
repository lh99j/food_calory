package com.example.food_calory.controller;

import com.example.food_calory.Service.FoodIntakeService;
import com.example.food_calory.model.FoodIntake;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-intake")
public class FoodIntakeController {
    private final FoodIntakeService foodIntakeService;

    @PostMapping("/create")
    public ResponseEntity<String> createFoodIntake(@RequestBody FoodIntake foodIntake) {
        try {
            foodIntakeService.addFoodIntake(foodIntake);

            return ResponseEntity.ok("음식 섭취 기록이 추가되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("음식 섭취 기록 추가 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<FoodIntake>> getFoodIntakeListByDate(@RequestParam("date") String date) {
        return ResponseEntity.ok(foodIntakeService.getFoodIntakeListByDate(date));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFoodIntakeByDateAndFoodName(@RequestParam("date") String date, @RequestParam("foodName") String foodName) {
        try {
            foodIntakeService.deleteDuplicateFoodIntakeByDateAndFoodName(date, foodName);
            return ResponseEntity.ok("음식 섭취 기록 삭제에 성공했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("음식 섭취 기록 삭제 중 오류가 발생했습니다.");
        }
    }
}
