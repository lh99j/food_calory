package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-intake")
public class FoodIntakeController {
    private final FoodIntakeService foodIntakeService;

    @PostMapping("/create")
    public String createFoodIntake(@RequestBody FoodIntake foodIntake) {
        try {
            foodIntakeService.addFoodIntake(foodIntake);

            return"음식 섭취 기록이 추가되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "음식 섭취 기록 추가 중 오류가 발생했습니다.";
        }
    }

    @GetMapping("/list")
    public List<FoodIntake> getFoodIntakeListByDate(@RequestParam("date") String date) {
        return foodIntakeService.getFoodIntakeListByDate(date);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFoodIntake(@PathVariable("id") Long id) {
        foodIntakeService.deleteFoodIntake(id);
    }
}
