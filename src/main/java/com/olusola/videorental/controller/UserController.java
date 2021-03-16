package com.olusola.videorental.controller;

import com.olusola.videorental.model.MovieSimple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    private final List<MovieSimple> MOVIES = List.of(
            new MovieSimple("Sherlock Homes", "www.sherlock.com"),
            new MovieSimple("Supper Man", "www.superman.com"),
            new MovieSimple("Thunder Bolt", "www.bolt.com")

    );

    @GetMapping("/movies")
    public List<MovieSimple> listAllMovie(){
        return MOVIES;
    }
}
