package com.olusola.videorental.repository;

import com.olusola.videorental.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<Cart, Long> {
}
