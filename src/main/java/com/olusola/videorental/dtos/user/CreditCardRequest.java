package com.olusola.videorental.dtos.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CreditCardRequest {
    @NotBlank
    @Size(min = 16, max = 40)
    private String number;

    @NotBlank
    @Size(min = 5, max = 10)
    private String validationDate;
}
