package com.olusola.videorental.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.olusola.videorental.security.AppUserPermission.*;

public enum AppUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(MOVIES_READ, MOVIES_WRITE, USER_WRITE, USER_READ)),
    ;

    private final Set<AppUserPermission> permissions;
    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities =  getPermissions()
                        .stream()
                        .map(appUserPermission -> new SimpleGrantedAuthority(appUserPermission.getPermission()))
                        .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(this.name())));
        return grantedAuthorities;
    }
}
