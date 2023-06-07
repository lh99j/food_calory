package com.example.food_calory;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FoodIntakeResponse {
    private Long id;
    private LocalDate createdDate;
    private String foodName;
    private String calorie;

    public FoodIntakeResponse(){

    }

    public FoodIntakeResponse(Long id, LocalDate createdDate, String foodName, String calorie) {
        this.id = id;
        this.createdDate = createdDate;
        this.foodName = foodName;
        this.calorie = calorie;
    }
}
