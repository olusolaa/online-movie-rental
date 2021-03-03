package com.olusola.videorental.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpDto {


    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

}
