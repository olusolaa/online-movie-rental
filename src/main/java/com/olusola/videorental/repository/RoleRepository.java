package com.olusola.videorental.repository;

import com.olusola.videorental.model.Role;
import com.olusola.videorental.security.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAppUserRole (AppUserRole name);
}
