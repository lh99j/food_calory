package com.example.food_calory.Service;

import com.example.food_calory.model.Food;
import com.example.food_calory.Repository.FoodRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
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

    public void saveFoodDataFromCSV() throws IOException, CsvException {
        ClassPathResource resource = new ClassPathResource("static/food.csv");
        File file = resource.getFile();
        List<Food> foodDataList = readFoodDataFromCSV(file);

        foodRepository.saveAll(foodDataList);
    }

    private List<Food> readFoodDataFromCSV(File file) throws IOException, CsvException {
        List<Food> foodDataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> lines = reader.readAll();
            for (String[] data : lines) {
                // 음식명과 1인분 칼로리 데이터만 추출하여 FoodData 객체 생성
                String foodName = data[0];
                String calorie = data[1];
                Food food = new Food();
                food.setFoodName(foodName);
                food.setCalorie(calorie);

                foodDataList.add(food);
            }
        } catch (IOException | CsvException e) {
            // 예외 처리
            throw e;
        }



        return foodDataList;
    }
}