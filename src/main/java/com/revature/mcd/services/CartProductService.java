package com.revature.mcd.services;

import com.revature.mcd.daos.CartProductDAO;
import com.revature.mcd.models.CartProduct;
import com.revature.mcd.util.annotations.Inject;

public class CartProductService {
    @Inject
    public final CartProductDAO cartProductDAO;
    public CartProductService(CartProductDAO cartProductDAO){
        this.cartProductDAO = cartProductDAO;
    }

    public void add(CartProduct cartProduct) {
        cartProductDAO.save(cartProduct);
    }

    public String getProductIdByCartID(String cart_id) {
        return cartProductDAO.getProductIdByCartID(cart_id);
    }
}
