package com.olusola.videorental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Setter
@Getter
public class Cart extends BaseModel{

    @OneToOne
    private User user;
}
