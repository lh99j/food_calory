package com.example.food_calory.controller;

import com.example.food_calory.Repository.FoodRepository;
import com.example.food_calory.Service.FoodService;
import com.example.food_calory.model.Food;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;

    @PostMapping("/createAll")
    public ResponseEntity<String> createAllFromCSV() {
        try {
            foodService.saveFoodDataFromCSV();
            return ResponseEntity.ok("CSV 데이터가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("CSV 데이터 저장 중 오류가 발생했습니다.");
        }
    }

    @GetMapping()
    public ResponseEntity<String> getCalorieByFoodName(@RequestParam String foodName) {
        Optional<Food> foodOptional = foodRepository.findByFoodName(foodName);

        if (foodOptional.isPresent()) {
            Food food = foodOptional.get();
            return ResponseEntity.ok(food.getCalorie());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //키워드를 통해 foodList 가져오기
    @GetMapping("/foodList")
    public ResponseEntity<String> getCalorieListByFoodName(@RequestParam String foodName) {
        List<Food> foodList = foodRepository.findByFoodNameContaining(foodName);

        if (!foodList.isEmpty()) {
            JSONArray jsonArray = new JSONArray();

            for (Food food : foodList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("foodName", food.getFoodName());
                jsonObject.put("calorie", food.getCalorie());
                jsonArray.put(jsonObject);
            }

            return ResponseEntity.ok(jsonArray.toString());
        } else {
            return ResponseEntity.ok("음식 리스트 가져오는 도중 오류가 발생했습니다.");
        }
    }

}
