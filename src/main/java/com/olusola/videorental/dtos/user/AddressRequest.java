package com.olusola.videorental.dtos.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AddressRequest {

    @NotBlank
    @Size(min = 4, max = 255)
    private String street;

    @NotBlank
    @Size(min = 2, max = 20)
    private String city;

    @NotBlank
    @Size(min = 4, max = 10)
    private String zip;

    @NotBlank
    @Size(min = 2, max = 20)
    private String state;

    @NotBlank
    @Size(min = 2, max = 20)
    private String country;


    AddressRequest(String street, String city, String zip, String state, String country) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    AddressRequest() {
    }
}
