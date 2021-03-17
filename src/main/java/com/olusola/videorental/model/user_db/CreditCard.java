package com.olusola.videorental.model.user_db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String validationDate;

    /*@ManyToOne
    private User user;*/



    public CreditCard(String number, String validationDate) {
        this.number = number;
        this.validationDate = validationDate;
    }
    public CreditCard() {
    }

}
