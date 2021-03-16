package com.olusola.videorental.dtos.movie;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Setter
@Getter
public class MovieRequest {

    @NotBlank
    @Size(min = 4, max = 64)
    private String movieId;

    @NotBlank
    @Size(min = 2, max = 64)
    private String title;

    private String rated;

    private Date releaseDate;

    private String movieUrl;

    private double price;

    @NotNull
    private Long categoryId;

}
