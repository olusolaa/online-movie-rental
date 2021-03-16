package com.olusola.videorental.model.user_db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@Table(name="billing_address")
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    private String street;


    @NotBlank
    @Size(max = 64)
    private String city;

    @NotBlank
    @Size(max = 64)
    private String zip;

    @NotBlank
    @Size(max = 64)
    private String state;

    @NotBlank
    @Size(max = 64)
    private String country;

    @JsonIgnore
    @OneToOne
    private User user;


    public BillingAddress(@NotBlank @Size(max = 64) String street, @NotBlank @Size(max = 64) String city,
                          @NotBlank @Size(max = 64) String zip, @NotBlank @Size(max = 64) String state,
                          @NotBlank @Size(max = 64) String country) {
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
    }

    public BillingAddress() {
        super();
    }
}
