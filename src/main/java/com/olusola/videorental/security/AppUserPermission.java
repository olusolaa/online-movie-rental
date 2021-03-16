package com.olusola.videorental.security;

public enum AppUserPermission {
    MOVIES_READ("movie:read"),
    MOVIES_WRITE("movie:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ;

    final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
