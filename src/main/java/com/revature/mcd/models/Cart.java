package com.revature.mcd.models;

import java.math.BigDecimal;

public class Cart {

    //region <attributes>
    private String id;
    private int quantity;
    private BigDecimal totalItemCost;
    private String user_id;
    //endregion

    //region <constructors>
    public Cart(){};

    public Cart(String id, int quantity, BigDecimal totalItemCost, String user_id) {
        this.id = id;
        this.quantity = quantity;
        this.totalItemCost = totalItemCost;
        this.user_id = user_id;
    }

    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(BigDecimal totalItemCost) {
        this.totalItemCost = totalItemCost;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //endregion
}
