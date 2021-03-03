package com.olusola.videorental.service.impl;

import com.olusola.videorental.repository.CartRepository;
import com.olusola.videorental.repository.UserRepository;
import com.olusola.videorental.service.CartService;
import com.olusola.videorental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;
    UserRepository userRepository;

    @Autowired

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }
}
