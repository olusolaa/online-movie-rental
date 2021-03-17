package com.olusola.videorental.repository;

import com.olusola.videorental.model.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Long> {
    UserPrivilege findByPermission(String permission);
}
