package com.olusola.videorental.dtos.movie;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CategoryRequest {

    @NotBlank
    @Size(max = 64)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;
}
