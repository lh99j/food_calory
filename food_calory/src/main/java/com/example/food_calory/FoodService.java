package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public void saveFoodDataFromCSV() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/food.csv");
        File file = resource.getFile();
        List<Food> foodDataList = readFoodDataFromCSV(file);

        foodRepository.saveAll(foodDataList);
    }

    public Optional<Food> getFoodData(String foodName) {
        return foodRepository.findByFoodName(foodName);
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