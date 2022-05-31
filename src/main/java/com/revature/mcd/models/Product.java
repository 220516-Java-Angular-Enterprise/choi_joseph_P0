package com.revature.mcd.models;

import java.math.BigDecimal;

public class Product {

    //region <attributes>
    private String id;
    private String name;
    private BigDecimal price;
    private int stock;
    private String description;
    private String supplier_id;
    //endregion

    //region <constructors>
    public Product(){ }

    public Product(String id, String name, BigDecimal price,
                   int stock, String description, String supplier_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.supplier_id = supplier_id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    //endregion

    //region <methods>

    @Override
    public String toString() {
        return id + ": " + name + "\n" + description +
                "\nPrice: " + price +
                "\nStock: " + stock;
    }

    //endregion
}
