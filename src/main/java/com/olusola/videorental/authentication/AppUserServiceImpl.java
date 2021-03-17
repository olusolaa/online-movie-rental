package com.olusola.videorental.authentication;

import com.olusola.videorental.model.User;
import com.olusola.videorental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mock-data")
public class AppUserServiceImpl implements AppUserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AppUserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return listAllAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    @Override
    public AppUser addUser(User user) {
        User savedUser = userRepository.save(user);

        return new AppUser(savedUser);
    }

    public List<AppUser> listAllAppUsers(){
        User user1 = new User("shola", passwordEncoder.encode("123456"));

        User user2 = new User("temi", passwordEncoder.encode("password"));
        //user2.setRole(ADMIN);

        User user3 = new User("john", passwordEncoder.encode("123456"));

        List<AppUser> appUsers = List.of(

                new AppUser(
                    user1
                ),

                new AppUser(
                    user2
                ),

                new AppUser(
                   user3
                )
        );

        return appUsers;
    }

}
