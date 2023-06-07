package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodRepository foodDao;
    private final FoodService foodService;

//    @GetMapping("/{foodName}")
//    public Food getFoodData(@PathVariable String foodName) {
//        return foodService.getFoodData(foodName);
//    }

    @PostMapping("/createAll")
    public String createAllFromCSV() {
        try {
            foodService.saveFoodDataFromCSV();
            return "CSV 데이터가 성공적으로 저장되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "CSV 데이터 저장 중 오류가 발생했습니다.";
        }
    }

    @GetMapping()
    public ResponseEntity<String> getCalorieByFoodName(@RequestParam String foodName) {
        Optional<Food> foodOptional = foodDao.findByFoodName(foodName);

        if (foodOptional.isPresent()) {
            Food food = foodOptional.get();
            return ResponseEntity.ok(food.getCalorie());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
