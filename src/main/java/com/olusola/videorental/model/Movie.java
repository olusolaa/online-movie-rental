package com.olusola.videorental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
public class Movie extends BaseModel {
    String title;
    String rated;
    String releaseDate;
    double rating;
    String type;
    String MovieUrl;

    @ManyToOne
    User users;
}
