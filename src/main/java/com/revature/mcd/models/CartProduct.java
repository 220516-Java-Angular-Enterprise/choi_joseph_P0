package com.revature.mcd.models;

public class CartProduct {
    //region <attributes>
    private String id;
    private String cart_id;
    private String product_id;
    //endregion

    //region <constructors>
    public CartProduct(){};

    public CartProduct(String id, String cart_id, String product_id) {
        this.id = id;
        this.cart_id = cart_id;
        this.product_id = product_id;
    }

    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    //endregion
}
