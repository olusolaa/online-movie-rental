package com.olusola.videorental.model.cart;

import com.olusola.videorental.model.product_db.Movie;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartLine {

        String subscriptionType;
        Movie movie;

        public int getWeigh(String subscriptionType){

            return switch (subscriptionType) {
                case "Monthly" -> 1;
                case "Quarterly" -> 3;
                case "Annually" -> 6;
                default -> throw new IllegalStateException("Unexpected value: " + subscriptionType);
            };
        }

}
