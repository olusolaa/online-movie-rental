package com.olusola.videorental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Movie extends BaseModel {
    String mId;
    String title;
    String rated;
    Date releaseDate;
    String category;
    String MovieUrl;

    @ManyToOne
    User users;
}
