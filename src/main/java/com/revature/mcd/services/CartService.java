package com.revature.mcd.services;

import com.revature.mcd.daos.CartDAO;
import com.revature.mcd.models.Cart;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;

public class CartService {
    @Inject
    private final CartDAO cartDAO;
    public CartService(CartDAO cartDAO){
        this.cartDAO = cartDAO;
    }

    public List<Cart> getCart(String id){

        return cartDAO.getUserCart(id);
    }

    // Add an item to a user's cart
    public void addToCart(Cart cart){
        cartDAO.save(cart);
    }

    public void remove(String id) {
        cartDAO.delete(id);
    }
}
