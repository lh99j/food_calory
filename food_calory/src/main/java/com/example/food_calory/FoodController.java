package com.example.food_calory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private FoodService foodService;

    @GetMapping("/food/{foodName}")
    public Food getFoodData(@PathVariable String foodName) {
        return foodService.getFoodData(foodName);
    }
}
