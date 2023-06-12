package com.example.food_calory.model;

import lombok.Data;

@Data
public class EmailVerification {
    public String email;
    public String password;
    public String authCode;
}
