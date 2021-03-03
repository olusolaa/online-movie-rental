package com.olusola.videorental.service.impl;

import com.olusola.videorental.dtos.SignInDto;
import com.olusola.videorental.dtos.SignUpDto;
import com.olusola.videorental.dtos.ResponseDto;
import com.olusola.videorental.exception.ResourceNotFoundException;
import com.olusola.videorental.model.User;
import com.olusola.videorental.repository.UserRepository;
import com.olusola.videorental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseDto signIn(SignInDto signInDto) {


            ResponseDto response = new ResponseDto();
            try{
                Optional<User> userDb1 = userRepository.findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword());

                if(userDb1.isPresent()){
                    response.setStatus(201);
                    response.setSuccessful(true);
                    response.setData(userDb1.get());
                    response.setUser(userDb1.get());
                    if (userDb1.get().getId() == 1){
                        response.setRole("Admin");
                    }
                    else {
                        response.setRole("Employee");
                    }
                    return  response;
                }
                throw new ResourceNotFoundException("Incorrect email or password");
            }catch (Exception e){
                response.setStatus(500);
                response.setSuccessful(false);
                response.setData(null);
                response.setError(e.getMessage());
                return response;
            }
    }

    @Override
    public ResponseDto signUp(SignUpDto signUpDto) {
            var response = new ResponseDto();
            try{
                var userInDb =  userRepository.findUserByEmail(signUpDto.getEmail());
                if(userInDb != null){
                    throw  new Exception("User Already Exists");
                }
                var user = new User();
                user.setUserName(signUpDto.getUserName());
                user.setEmail(signUpDto.getEmail());
                user.setPassword(signUpDto.getPassword());
                var registeredUser =  userRepository.save(user);
                response.setStatus(201);
                response.setSuccessful(true);
                response.setData(registeredUser);
                return  response;
            }catch (Exception e){
                response.setStatus(500);
                response.setSuccessful(false);
                response.setData(null);
                response.setError(e.getMessage());
                return response;
            }
        }
}
