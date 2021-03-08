package com.olusola.videorental.repository;

import com.olusola.videorental.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByTitle(String title);
    List<Movie> findAllByCategoryContains(String category);
    List<Movie> findAllByRated(String rated);
    List<Movie> findAllByReleaseDateAfter(Date date);
}
