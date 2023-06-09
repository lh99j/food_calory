package com.example.food_calory.Service;

import com.example.food_calory.model.BaseResponse;
import com.example.food_calory.model.EmailVerification;
import com.example.food_calory.model.User;
import com.example.food_calory.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private String authNum;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    public void createCode() {
        StringBuilder sb = new StringBuilder(6);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        authNum = sb.toString();
    }

    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "grand2181@gmail.com";
        String title = "회원가입 인증 번호";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);
        message.setFrom(setFrom);
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    public BaseResponse sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        MimeMessage emailForm = createEmailForm(toEmail);

        String key = "verification:" + toEmail;
        //10분동안 redis에 key와 authNum을 저장
        redisTemplate.opsForValue().set(key, authNum, Duration.ofMinutes(10));

        //실제 메일 전송
        emailSender.send(emailForm);

        BaseResponse response = new BaseResponse();

        response.setResultCode(0);
        response.setDesc("인증번호 전송이 완료되었습니다.");

        return response;
    }

    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context);
    }

    public BaseResponse verifyEmail(EmailVerification verification) {
        String key = "verification:" + verification.getEmail();
        String savedCode = redisTemplate.opsForValue().get(key);
        BaseResponse response = new BaseResponse();

        if (savedCode != null && savedCode.equals(verification.getAuthCode())) {
            saveUser(verification);
            redisTemplate.delete(key);
            response.setResultCode(0);
            response.setDesc("이메일이 성공적으로 인증되었습니다.");
        }else{
            response.setResultCode(-1);
            response.setDesc("이메일 인증에 실패했습니다.");
        }

        return response;
    }


    private void saveUser(EmailVerification verification) {
        User user = new User();
        user.setEmail(verification.getEmail());
        user.setPassword(verification.getPassword());
        userRepository.save(user);
    }

}
