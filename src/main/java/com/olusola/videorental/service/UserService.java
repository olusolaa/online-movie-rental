package com.olusola.videorental.service;


import com.olusola.videorental.dtos.SignInDto;
import com.olusola.videorental.dtos.SignUpDto;
import com.olusola.videorental.dtos.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseDto signIn(SignInDto signInDto);
    ResponseDto signUp(SignUpDto signUpDto);
}
