package com.olusola.videorental.dtos.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserSignUpRequest {
    @NotBlank(message = "Firstname cannot be empty!")
    @Size(min = 4, max = 40)
    @Order(1)
    private String firstName;

    @NotBlank(message = "Lastname cannot be empty!")
    @Size(min = 4, max = 40)
    @Order(2)
    private String lastName;

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 4, max = 40)
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank(message = "Phone cannot be empty!")
    @Size(max = 40)
    private String phone;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}
