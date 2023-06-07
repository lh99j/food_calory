package com.example.food_calory;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private String calorie;

    public Food() {
    }

    public Food(String foodName, String calorie) {
        this.foodName = foodName;
        this.calorie = calorie;
    }
}
