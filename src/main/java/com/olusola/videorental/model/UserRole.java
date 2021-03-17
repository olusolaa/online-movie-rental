package com.olusola.videorental.model;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static com.olusola.videorental.security.AppUserRole.USER;

@Entity
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserPrivilege> privileges;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();
    public UserRole() {
        this(USER.name(), Sets.newHashSet());
    }

    public UserRole(String name, Set<UserPrivilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }


    public Set<UserPrivilege> getPrivileges(){
        return privileges;
    }


    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPrivileges()
                      .stream()
                      .map(userPrivilege -> new SimpleGrantedAuthority(userPrivilege.getPermission()))
                      .collect(Collectors.toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(name)));

        return grantedAuthorities;

    }


}
