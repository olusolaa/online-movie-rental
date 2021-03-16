package com.olusola.videorental.dtos.shopping;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ShoppingRequest {

    private String cartId;

    @NotBlank
    @Size(min = 4, max = 64)
    private String MovieId;

    @NotNull
    String subscriptionType;
}
