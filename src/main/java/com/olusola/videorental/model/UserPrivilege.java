package com.olusola.videorental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();


    public UserPrivilege() {
        this("");
    }

    public UserPrivilege(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static UserPrivilege readMovie(){
        return new UserPrivilege("movies:read");
    }

    public static UserPrivilege writeMovie(){
        return new UserPrivilege("movies:write");
    }

    public static UserPrivilege readUser(){
        return new UserPrivilege("user:read");
    }

    public static UserPrivilege writeUser(){
        return new UserPrivilege("user:write");
    }

}
