package com.revature.mcd.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;

public class Order {
    //region <attributes>
    String id;
    Date orderDate;
    BigDecimal orderCost;
    String user_id;
    //endregion

    //region <constructors>
    public Order(){};

    public Order(String id, Date orderDate, BigDecimal orderCost, String user_id) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderCost = orderCost;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    //endregion

    @Override
    public String toString() {
        return "Date: " + orderDate + "\nCost: " + orderCost;
    }
}
