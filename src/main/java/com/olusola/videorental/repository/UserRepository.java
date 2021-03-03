package com.olusola.videorental.repository;

import com.olusola.videorental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmailAndPassword(String userName, String password);
    User findUserByEmail(String userName);
}
