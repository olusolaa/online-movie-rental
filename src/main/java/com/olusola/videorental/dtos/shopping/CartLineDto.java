package com.olusola.videorental.dtos.shopping;

import com.olusola.videorental.dtos.movie.MovieRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartLineDto {

    String subscriptionType;
    MovieRequest movie;
}
