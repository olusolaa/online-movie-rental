package com.olusola.videorental.service;

import com.olusola.videorental.dtos.ResponseDto;
import com.olusola.videorental.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    ResponseDto addMovie(Movie movie);

    List<Movie> allMovies();
}
