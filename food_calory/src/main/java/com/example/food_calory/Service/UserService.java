package com.example.food_calory.Service;

import com.example.food_calory.Repository.UserRepository;
import com.example.food_calory.model.LoginRequest;
import com.example.food_calory.model.BaseResponse;
import com.example.food_calory.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public BaseResponse authenticateUser(LoginRequest loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email);

        BaseResponse response = new BaseResponse();

        if (user == null || !user.getPassword().equals(password)) {
            response.setResultCode(-1);
            response.setDesc("이메일 또는 비밀번호를 확인해주세요");

        }else{
            response.setResultCode(0);
            response.setDesc("로그인 성공");
            response.setBody(user);
        }

        return response;
    }


}
