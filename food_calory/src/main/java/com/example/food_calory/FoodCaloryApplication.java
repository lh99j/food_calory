package com.example.food_calory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class FoodCaloryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodCaloryApplication.class, args);
    }

}
