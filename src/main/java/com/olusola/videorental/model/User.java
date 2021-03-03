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
public class User extends BaseModel{
    String accountDetails; // I think this should be an entity
    String userName;
    String email;
    String password;
}
