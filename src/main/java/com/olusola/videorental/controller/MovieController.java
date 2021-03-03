package com.olusola.videorental.controller;

import com.olusola.videorental.dtos.SignInDto;
import com.olusola.videorental.model.Movie;
import com.olusola.videorental.service.CartService;
import com.olusola.videorental.service.MovieService;
import com.olusola.videorental.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class MovieController {

    private CartService cartService;
    private MovieService movieService;
    private UserService userService;

    @Autowired
    public MovieController(CartService cartService, MovieService movieService, UserService userService) {
        this.cartService = cartService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @GetMapping(path = "admin/add-movie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @GetMapping(path = "admin/movie-list")
    public String listMovie(Model model) {
        model.addAttribute("movie", movieService.allMovies());
        return "movie-list";
    }
}
