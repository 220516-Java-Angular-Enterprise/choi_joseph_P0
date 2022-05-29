package com.revature.mcd.models;

import java.math.BigDecimal;

public class Product {

    //region <attributes>
    private String id;
    private String name;
    private BigDecimal price;
    //endregion

    //region <constructors>
    public Product(){ }

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    //endregion

    //region <accessors and mutators>
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    //endregion

    //region <methods>
    //endregion
}
