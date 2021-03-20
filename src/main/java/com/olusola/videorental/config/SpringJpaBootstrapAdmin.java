package com.olusola.videorental.config;

import com.google.common.collect.Sets;
import com.olusola.videorental.authentication.MyUserDetail;
import com.olusola.videorental.authentication.UserService;
import com.olusola.videorental.model.User;
import com.olusola.videorental.model.UserPrivilege;
import com.olusola.videorental.model.Role;
import com.olusola.videorental.repository.UserPrivilegeRepository;
import com.olusola.videorental.repository.RoleRepository;
import com.olusola.videorental.security.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.olusola.videorental.security.AppUserPermission.*;
import static com.olusola.videorental.security.AppUserRole.*;
import static com.olusola.videorental.security.AppUserRole.ADMIN;

/**
 * Adds the first
 * user as admin
 * to database
 */


//@Configuration
@Component
public class SpringJpaBootstrapAdmin implements ApplicationListener<ContextRefreshedEvent> {
    private boolean dataAlreadySetup = false;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserPrivilegeRepository previlegeRepository;

    @Autowired
    public SpringJpaBootstrapAdmin(@Qualifier("jpa") UserService userService, RoleRepository roleRepository, UserPrivilegeRepository previlegeRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.previlegeRepository = previlegeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadFirstUserAsAdmin();
    }

    private void loadFirstUserAsAdmin() {
        List<User> users = userService.listAllAppUsers();

        if (dataAlreadySetup || users.size() > 0) return;

//         UserPrivilege movieReadPrivilege = createPrivilegeIFNotFound(MOVIES_READ.getPermission());
//         UserPrivilege movieWritePrivilege = createPrivilegeIFNotFound(MOVIES_WRITE.getPermission());
//         UserPrivilege userReadPrivilege = createPrivilegeIFNotFound(USER_READ.getPermission());
//         UserPrivilege userWritePrivilege = createPrivilegeIFNotFound(USER_WRITE.getPermission());

        User temi = new User("temi", "password");
        temi.setFirstname("Temilola");
        temi.setLastname("Ajayi");


        //student privilege
        User shola = new User("shola", "123456");
        shola.setFirstname("Solanke");
        shola.setLastname("Ajayi");
//
//        Set<UserPrivilege> adminPrivileges  =
//                Sets.newHashSet(movieReadPrivilege, movieWritePrivilege, userReadPrivilege, userWritePrivilege);
//

        createUserRowIFNotFound(AppUserRole.ADMIN);
        createUserRowIFNotFound(AppUserRole.USER);

        Role adminRole = roleRepository.findByAppUserRole(AppUserRole.ADMIN);
        Role userRole = roleRepository.findByAppUserRole(AppUserRole.USER);

        temi.setRoles(Collections.singletonList(adminRole));
        shola.setRoles(Collections.singletonList(userRole));


        userService.addUser(temi);
        userService.addUser(shola);

        dataAlreadySetup =   true;

        System.out.println(temi.getRoles().get(0).getAppUserRole().name());

    }

    private Role createUserRowIFNotFound(AppUserRole name){
        Role role = roleRepository.findByAppUserRole(name);
        if(role == null) {
            Role role1 = new Role();
            role1.setAppUserRole(name);
            roleRepository.save(role1);
        }
        return role;
    }
}
