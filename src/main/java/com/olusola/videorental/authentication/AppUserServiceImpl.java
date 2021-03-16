package com.olusola.videorental.authentication;

import com.olusola.videorental.security.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.olusola.videorental.security.AppUserRole.ADMIN;
import static com.olusola.videorental.security.AppUserRole.USER;

@Service("mock-data")
public class AppUserServiceImpl implements AppUserService{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return listAllAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    public List<AppUser> listAllAppUsers(){
        List<AppUser> appUsers = List.of(
                new AppUser(
                    "shola",
                    passwordEncoder.encode("123456" ),
                    USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                    "temi",
                    passwordEncoder.encode("password" ),
                    ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),

                new AppUser(
                    "john",
                    passwordEncoder.encode("password" ),
                    USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return appUsers;
    }
}
