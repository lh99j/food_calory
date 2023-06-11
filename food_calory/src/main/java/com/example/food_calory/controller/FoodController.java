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

    @GetMapping("/foodList")
    public String getCalorieListByFoodName(@RequestParam String foodName) {
        List<Food> foodList = foodDao.findByFoodNameContaining(foodName);

        if (!foodList.isEmpty()) {
            JSONArray jsonArray = new JSONArray();

            for (Food food : foodList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("foodName", food.getFoodName());
                jsonObject.put("calorie", food.getCalorie());
                jsonArray.put(jsonObject);
            }

            return jsonArray.toString();
        } else {
            return "음식 리스트 가져오는 도중 오류가 발생했습니다.";
        }
    }

}
