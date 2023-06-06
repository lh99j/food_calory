package com.example.food_calory;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private FoodDao foodDao;

    private ResourceLoader resourceLoader;

    public void initializeDataFromCSV() {
        try {
            List<String[]> records = readCSV();
            List<Food> foodList = extractFoodData(records);
            saveFoodData(foodList);
        } catch (IOException e) {
            // 예외 처리
        }
    }

    private List<String[]> readCSV() throws IOException {
        List<String[]> records = new ArrayList<>();

        String filePath = ResourceUtils.getFile("classpath:filename.csv").getPath();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }

        return records;
    }

    private List<Food> extractFoodData(List<String[]> records) {
        List<Food> foodDataList = new ArrayList<>();
        for (String[] record : records) {
            String foodName = record[0];
            String calorie = record[1];

            Food food = new Food();
            food.setFoodName(foodName);
            food.setCalorie(calorie);

            foodDataList.add(food);
        }
        return foodDataList;
    }

    private void saveFoodData(List<Food> foodDataList) {
        foodDao.saveAll(foodDataList);
    }

    public Food getFoodData(String foodName) {
        return foodDao.findByFoodName(foodName);
    }
}
