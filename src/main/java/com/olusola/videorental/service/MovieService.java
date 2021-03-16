package com.olusola.videorental.service;

import com.olusola.videorental.dtos.movie.CategoryRequest;
import com.olusola.videorental.dtos.movie.MovieRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public interface MovieService {

    ResponseEntity<?> addMovie(MovieRequest movieRequest);
    ResponseEntity<?> updateMovie(MovieRequest movieRequest, Long productId);
    ResponseEntity<?> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<?> updateCategory(CategoryRequest categoryRequest, Long catId);
    ResponseEntity<?> getCategory(Optional<Integer> categoryId);
    ResponseEntity<?> getMovie(Optional<String> productId);
    ResponseEntity<?> getMovieById(Long id);
    ResponseEntity<?> viewMoviesByRated(Optional<String> rated);
    ResponseEntity<?> viewMovieByReleasedDate(Optional<Date> productId);



}
