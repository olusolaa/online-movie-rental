package com.olusola.videorental.authentication;

import com.olusola.videorental.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User addUser(User user);
    List<User> listAllAppUsers();

}
