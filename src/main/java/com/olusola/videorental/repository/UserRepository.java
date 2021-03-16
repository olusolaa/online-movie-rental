package com.olusola.videorental.repository;

import com.olusola.videorental.model.user_db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
//    List<User> findByIdIn(List<Long> userIds);
    User findByUserId(Long id);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
