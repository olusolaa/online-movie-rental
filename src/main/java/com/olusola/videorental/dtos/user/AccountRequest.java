package com.olusola.videorental.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountRequest {
    private String username;
    private String email;
    private String name;
    private String password;

    public AccountRequest(String username, String email, String fullName, String password) {
        super();
        this.username = username;
        this.email = email;
        this.name = fullName;
        this.password = password;
    }

    public AccountRequest() {
        super();
    }
}
