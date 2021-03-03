package com.olusola.videorental.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Viewed extends BaseModel{
    Long quantity;

}
