/*
package com.olusola.videorental.authentication;

import com.olusola.videorental.model.User;
import com.olusola.videorental.repository.UserRepository;
import com.olusola.videorental.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mock-data")
public class UserServiceImplMock implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplMock(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Optional<UserDetailImpl> selectAppUserByUsername(String username) {
        return listAllAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    @Override
    public UserDetailImpl addUser(User user) {
        User savedUser = userRepository.save(user);

        return new UserDetailImpl(savedUser);
    }

    public List<UserDetailImpl> listAllAppUsers(){
        User user1 = new User("shola", passwordEncoder.encode("123456"));

        User user2 = new User("temi", passwordEncoder.encode("password"));
        //user2.setRole(ADMIN);

        User user3 = new User("john", passwordEncoder.encode("123456"));

        List<UserDetailImpl> myUserDetails = List.of(

                new UserDetailImpl(
                    user1
                ),

                new UserDetailImpl(
                    user2
                ),

                new UserDetailImpl(
                   user3
                )
        );

        return myUserDetails;
    }

}
*/
