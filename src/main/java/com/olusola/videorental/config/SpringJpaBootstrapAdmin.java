package com.olusola.videorental.config;

import com.google.common.collect.Sets;
import com.olusola.videorental.authentication.AppUser;
import com.olusola.videorental.authentication.AppUserService;
import com.olusola.videorental.model.User;
import com.olusola.videorental.model.UserPrivilege;
import com.olusola.videorental.model.UserRole;
import com.olusola.videorental.repository.UserPrivilegeRepository;
import com.olusola.videorental.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
    private final AppUserService userService;
    private final UserRoleRepository roleRepository;
    private final UserPrivilegeRepository previlegeRepository;

    @Autowired
    public SpringJpaBootstrapAdmin(@Qualifier("jpa") AppUserService userService, UserRoleRepository roleRepository, UserPrivilegeRepository previlegeRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.previlegeRepository = previlegeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadFirstUserAsAdmin();
    }

    private void loadFirstUserAsAdmin() {
        List<AppUser> users = userService.listAllAppUsers();
        
        if (dataAlreadySetup || users.size() > 0) return;

         UserPrivilege movieReadPrivilege = createPrivilegeIFNotFound(MOVIES_READ.getPermission());
         UserPrivilege movieWritePrivilege = createPrivilegeIFNotFound(MOVIES_WRITE.getPermission());
         UserPrivilege userReadPrivilege = createPrivilegeIFNotFound(USER_READ.getPermission());
         UserPrivilege userWritePrivilege = createPrivilegeIFNotFound(USER_WRITE.getPermission());

        User temi = new User("temi", "password");
        temi.setFirstname("Temilola");
        temi.setLastname("Ajayi");

        //student privilege
        User shola = new User("shola", "123456");
        shola.setFirstname("Solanke");
        shola.setLastname("Ajayi");

        Set<UserPrivilege> adminPrivileges  =
                Sets.newHashSet(movieReadPrivilege, movieWritePrivilege, userReadPrivilege, userWritePrivilege);


        createUserRowIFNotFound(ADMIN.name(), adminPrivileges);
        createUserRowIFNotFound(USER.name(), Sets.newHashSet(movieReadPrivilege));

        UserRole adminRole = roleRepository.findByName(ADMIN.name());
        UserRole userRole = roleRepository.findByName(USER.name());

        temi.setRoles(Sets.newHashSet(adminRole));
        shola.setRoles(Sets.newHashSet(userRole));

          
        userService.addUser(temi);
        userService.addUser(shola);

        dataAlreadySetup =   true;

    }

    private UserPrivilege createPrivilegeIFNotFound(String name) {
        UserPrivilege privilege = previlegeRepository.findByPermission(name);
        if(privilege == null) {
            privilege = new UserPrivilege(name);
           privilege = previlegeRepository.save(privilege);
        }
        return privilege;
    }

    private UserRole createUserRowIFNotFound(String name, Set<UserPrivilege> privileges){
        UserRole role = roleRepository.findByName(name);
        if(role == null) {
            role = new UserRole(name, privileges);
           role = roleRepository.save(role);
        }
        return role;
    }
}
