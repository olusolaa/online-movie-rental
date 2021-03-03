package com.olusola.videorental.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
public class UserDto {


    @NotBlank
    private String email;

    private Long id;

}
