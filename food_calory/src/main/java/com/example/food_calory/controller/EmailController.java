package com.example.food_calory.controller;

import com.example.food_calory.Service.EmailService;
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
    public String sendVerificationCode(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendEmail(email);
        return authCode;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailVerification verification) {
        boolean isVerified = emailService.verifyEmail(verification);

        if (isVerified) {
            return ResponseEntity.ok("이메일이 성공적으로 인증되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("이메일 인증에 실패했습니다.");
        }
    }
}
