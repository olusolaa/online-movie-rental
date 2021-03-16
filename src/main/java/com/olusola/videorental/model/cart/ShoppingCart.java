package com.olusola.videorental.model.cart;

import com.olusola.videorental.model.product_db.Movie;

import javax.persistence.Id;
import java.util.ArrayList;

public class ShoppingCart {

        @Id
        String cartId;

        Long customerId;

        Boolean ordered = false;





        ArrayList<CartLine> cartlineList = new ArrayList<>();

        public void addToCart(Movie movie, String subscriptionType) {
            for (CartLine cline : cartlineList) {
                if (cline.getMovie().getMovieId().equals(movie.getMovieId())) {
                    // Movie already exist in the cart.
                    return;
                }
            }
            CartLine cline = new CartLine();
            cline.setMovie(movie);
            cline.setSubscriptionType(subscriptionType);
            cartlineList.add(cline);
        }


        public double getTotalPrice(){
            double totalPrice = 0.0;
            for (CartLine cline : cartlineList) {
                totalPrice=totalPrice+(cline.getMovie().getPrice() * cline.getWeigh(cline.getSubscriptionType()));
            }
            return totalPrice;
        }

        public void removeFromCart(Movie movie){
            cartlineList.removeIf(cline -> cline.getMovie().getMovieId().equals(movie.getMovieId()));
            }
}
