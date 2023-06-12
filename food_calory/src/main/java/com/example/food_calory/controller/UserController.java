package com.example.food_calory.controller;

import com.example.food_calory.Service.UserService;
import com.example.food_calory.model.LoginRequest;
import com.example.food_calory.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequest loginRequestDto){
        return userService.authenticateUser(loginRequestDto);
    }
}
