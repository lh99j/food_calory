package com.example.food_calory.model;

import lombok.Data;

@Data
public class BaseResponse {
    private Integer resultCode;
    private String desc;
    private Object body;
}

