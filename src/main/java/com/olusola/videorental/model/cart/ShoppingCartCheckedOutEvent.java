package com.olusola.videorental.model.cart;

import java.util.Date;


public class ShoppingCartCheckedOutEvent {
    private ShoppingCart cart;
    private Date date= new Date();


    public ShoppingCart getCart() {
        return cart;
    }

    public Date getDate() {
        return date;
    }

    public ShoppingCartCheckedOutEvent(ShoppingCart cart) {
        this.cart = cart;
    }
}
