package com.example.food_calory.controller;

import com.example.food_calory.Service.EmailService;
import com.example.food_calory.model.BaseResponse;
import com.example.food_calory.model.EmailVerification;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signUp/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendCode")
    public ResponseEntity<BaseResponse> sendVerificationCode(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(emailService.sendEmail(email));
    }

    @PostMapping("/verify")
    public ResponseEntity<BaseResponse> verifyEmail(@RequestBody EmailVerification verification) {
        return ResponseEntity.ok(emailService.verifyEmail(verification));
    }
}
