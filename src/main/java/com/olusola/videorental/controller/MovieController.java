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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


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

    @PostMapping(path = "add-movies")
    public String addMoviePost(@ModelAttribute("movie") Movie movie){
        movieService.addMovie(movie);
        return "movie-list";
    }

    @GetMapping(path = "view/movie-list")
    public String listMovie(Model model) {
        model.addAttribute("movie", movieService.allMovies());
        return "add-movie";
    }

    @GetMapping(path = "/view/movie/{title}")
    public String viewMovieByTitle(@PathVariable String title){
        movieService.getMovieByTitle(title);
        return null;
    }


    @GetMapping(path ="/view/movie/{category}")
    public String viewByCategory(@PathVariable String category){
        movieService.viewMoviesByCategory(category);
        return null;
    }
    @GetMapping(path = "/view/movie/{releaseDate}")
    public String viewByReleaseDate(@PathVariable Date date ){
        movieService.viewMoviesByReleasedDate(date);
        return null;
    }

    @GetMapping(path = "/view/movie/{releaseDate}")
    public String viewByRated(@PathVariable String rated ){
        movieService.viewMoviesByRated(rated);
        return null;
    }





}
