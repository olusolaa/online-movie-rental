package com.olusola.videorental.model;

import com.olusola.videorental.security.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserPrivilege> userPrivileges = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Set<UserPrivilege> getUserPrivileges() {
        return userPrivileges;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthories() {
        Set<SimpleGrantedAuthority> grantedAuthorities = getUserPrivileges()
                .stream()
                .map(userPrivilege -> new SimpleGrantedAuthority(userPrivilege.getPermission()))
                .collect(Collectors.toSet());

        Set<SimpleGrantedAuthority> grantedRoles = getRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getName()))
                .collect(Collectors.toSet());

        grantedAuthorities.add(grantedRoles.stream().findFirst().get());

        return grantedAuthorities;
    }
}
