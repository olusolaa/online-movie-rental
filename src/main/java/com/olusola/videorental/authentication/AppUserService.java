package com.olusola.videorental.authentication;

import java.util.Optional;

public interface AppUserService {
    Optional<AppUser> selectAppUserByUsername(String username);
}
