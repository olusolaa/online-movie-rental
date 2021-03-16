package com.olusola.videorental.service.impl;

import com.olusola.videorental.dtos.ApiResponse;
import com.olusola.videorental.dtos.movie.CategoryRequest;
import com.olusola.videorental.dtos.movie.MovieRequest;
import com.olusola.videorental.model.product_db.Category;
import com.olusola.videorental.model.product_db.Movie;
import com.olusola.videorental.repository.CategoryRepository;
import com.olusola.videorental.repository.MovieRepository;
import com.olusola.videorental.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    CategoryRepository categoryRepository;

    @Autowired

    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<?> addMovie(MovieRequest movieRequest) {

        Movie movie = new Movie(movieRequest.getMovieId(), movieRequest.getTitle(),
                movieRequest.getRated(), movieRequest.getReleaseDate(),
                movieRequest.getMovieUrl(), movieRequest.getPrice(),
                categoryRepository.findById(movieRequest.getCategoryId()).orElse(null));
        Movie result = movieRepository.save(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/movie/{movieId}")
                .buildAndExpand(result.getMovieId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,
                "Movie has been added successfully!"));
    }


    @Override
    public ResponseEntity<?> updateMovie(MovieRequest movieRequest, Long productId) {
        Movie movie = movieRepository.findById(productId).orElse(null);
        if (movie != null) {
            movie.setMovieId(movieRequest.getMovieId());
            movie.setTitle(movieRequest.getTitle());
            movie.setMovieUrl(movieRequest.getMovieUrl());
            movie.setPrice(movieRequest.getPrice());
            movie.setRated(movie.getRated());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setCategory(categoryRepository.findById(movieRequest.getCategoryId()).orElse(null));
            Movie result = movieRepository.save(movie);

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/movie/{movieId}")
                    .buildAndExpand(result.getMovieId()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true,
                    "Movie has been updated successfully!"));
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "Specified movie is not available!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> addCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            return new ResponseEntity(new ApiResponse(false, "Already there " +
                    "is category named " + categoryRequest.getName()), HttpStatus.BAD_REQUEST);
        }
        Category result = categoryRepository
                .save(new Category(categoryRequest.getName(), categoryRequest.getDescription()));

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/category/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,
                "Category has been added successfully!"));
    }

    @Override
    public ResponseEntity<?> updateCategory(CategoryRequest categoryRequest, Long catId) {
        Category category = categoryRepository.findById(catId).orElse(null);
        if (category != null) {
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            Category result = categoryRepository.save(category);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/category/{id}")
                    .buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true,
                    "Category has been updated successfully!"));
        }

        return new ResponseEntity(
                new ApiResponse(false, "There is no such  category found!" + catId),
                HttpStatus.BAD_REQUEST);    }

    @Override
    public ResponseEntity<?> getCategory(Optional<Integer> categoryId) {
        if(!categoryId.isPresent()) {
            List<Category> categories = categoryRepository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        Optional<Category> category   = categoryRepository.findById(Long.valueOf(categoryId.get()));
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new
                ResponseEntity(new ApiResponse(false, "Specified category is not available!"),
                HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<?> getMovie(Optional<String> productId) {
        if(!productId.isPresent()) {
            List<Movie> movies = movieRepository.findAll();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }

        Optional<Movie> result = movieRepository.findByMovieId(productId.get());

        if(productId.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return new ResponseEntity(new ApiResponse(false, "Specified movie is not available!"),
                    HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getMovieById(Long id) {
        Optional<Movie> result = movieRepository.findById(id);

        if(result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return new ResponseEntity(new ApiResponse(false, "Specified movie is not available!"),
                    HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> viewMoviesByRated(Optional<String> rated) {
        return null;
    }

    @Override
    public ResponseEntity<?> viewMovieByReleasedDate(Optional<Date> productId) {
        return null;
    }
}
