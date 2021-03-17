package com.olusola.videorental.service.impl;

import com.olusola.videorental.authentication.AppUser;
import com.olusola.videorental.authentication.AppUserService;
import com.olusola.videorental.model.User;
import com.olusola.videorental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("jpa")
public class UserServiceImpl implements AppUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return Optional.of(new AppUser(user));
    }

    @Override
    public AppUser addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new AppUser(userRepository.save(user));
    }

    @Override
    public List<AppUser> listAllAppUsers() {
        return userRepository.findAll().stream().map(AppUser::new).collect(Collectors.toList());
    }

}
