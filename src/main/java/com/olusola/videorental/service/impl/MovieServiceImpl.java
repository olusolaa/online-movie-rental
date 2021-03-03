package com.olusola.videorental.service.impl;

import com.olusola.videorental.dtos.ResponseDto;
import com.olusola.videorental.model.Movie;
import com.olusola.videorental.repository.MovieRepository;
import com.olusola.videorental.repository.UserRepository;
import com.olusola.videorental.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    UserRepository userRepository;

    @Autowired

    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto addMovie(Movie movie) {
        var response = new ResponseDto();
        try {
                var movieInDb =  movieRepository.findMovieByTitle(movie);

                if(movieInDb != null){
                    throw  new Exception("Movie Already Exists");
                }
                var movie1 = new Movie();
                movie1.setMovieUrl(movie.getMovieUrl());
                movie1.setRated(movie.getRated());
                movie1.setReleaseDate(movie.getReleaseDate());
                movie1.setTitle(movie.getTitle());
                movie1.setType(movie.getType());
                var addedMovie =  movieRepository.save(movie1);
            response.setStatus(201);
            response.setSuccessful(true);
            response.setData(addedMovie);
            return  response;
        }catch (Exception e) {
            response.setStatus(500);
            response.setSuccessful(false);
            response.setData(null);
            response.setError(e.getMessage());
            return response;
        }
    }

    @Override
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }


}
