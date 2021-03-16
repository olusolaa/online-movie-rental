package com.olusola.videorental.repository;

import com.olusola.videorental.dtos.user.role.RoleName;
import com.olusola.videorental.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
