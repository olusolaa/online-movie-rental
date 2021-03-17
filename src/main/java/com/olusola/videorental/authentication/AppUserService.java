package com.olusola.videorental.authentication;

import com.olusola.videorental.model.User;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> selectAppUserByUsername(String username);

    AppUser addUser(User user);

    List<AppUser> listAllAppUsers();

}
