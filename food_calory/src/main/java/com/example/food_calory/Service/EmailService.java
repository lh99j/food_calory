package com.example.food_calory.Service;

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
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "grand2181@gmail.com";
        String toEmail = email;
        String title = "회원가입 인증 번호";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);
        message.setFrom(setFrom);
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        MimeMessage emailForm = createEmailForm(toEmail);

        String key = "verification:" + toEmail;
        redisTemplate.opsForValue().set(key, authNum, Duration.ofMinutes(10));

        //실제 메일 전송
        emailSender.send(emailForm);

        return authNum;
    }

    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context);
    }

    public boolean verifyEmail(EmailVerification verification) {
        String key = "verification:" + verification.getEmail();
        String savedCode = redisTemplate.opsForValue().get(key);

        if (savedCode != null && savedCode.equals(verification.getAuthCode())) {
            saveUser(verification);
            redisTemplate.delete(key);

            return true;
        }

        return false;
    }


    private void saveUser(EmailVerification verification) {
        userRepository.save(new User(verification.getEmail(), verification.getPassword()));
    }

}
