package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final FoodDao foodDao;

//    @GetMapping("/{foodName}")
//    public Food getFoodData(@PathVariable String foodName) {
//        return foodService.getFoodData(foodName);
//    }

    @PostMapping("/createAll")
    public String createAllFromCSV() {
        try {
            ClassPathResource resource = new ClassPathResource("static/food.csv");
            File file = resource.getFile();
            List<Food> foodDataList = readFoodDataFromCSV(file);

            foodDao.saveAll(foodDataList);

            return "CSV 데이터가 성공적으로 저장되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "CSV 데이터 저장 중 오류가 발생했습니다.";
        }
    }

    @GetMapping("/{foodName}")
    public ResponseEntity<String> getCalorieByFoodName(@PathVariable String foodName) {
        Optional<Food> foodOptional = foodDao.findByFoodName(foodName);

        if (foodOptional.isPresent()) {
            Food food = foodOptional.get();
            return ResponseEntity.ok(food.getCalorie());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private List<Food> readFoodDataFromCSV(File file) throws IOException {
        List<Food> foodDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // 음식명과 1인분 칼로리 데이터만 추출하여 FoodData 객체 생성
                String foodName = data[0];
                String calorie = data[1];
                Food food = new Food(foodName, calorie);

                foodDataList.add(food);
            }
        }

        return foodDataList;
    }


}
