package com.olusola.videorental.model.product_db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.olusola.videorental.model.product_db.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "movie", uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_id" }) })
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "movie_id")
    private String movieId;

    @NotBlank
    @Size(max = 64)
    private String title;

    private String rated;

    private Date releaseDate;

    private String MovieUrl;

    @Column(precision = 16, scale = 2)
    private Double price;


    @JsonInclude()
    @Transient
    private Long categoryId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;





    public Movie(String movieId, String title, String rated, Date releaseDate, String movieUrl, double price, Category category) {
        this.movieId = movieId;
        this.title = title;
        this.rated = rated;
        this.releaseDate = releaseDate;
        MovieUrl = movieUrl;
        this.category = category;
    }

    public Movie() {
        
    }
}
