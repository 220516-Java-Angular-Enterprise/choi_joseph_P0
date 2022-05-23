package com.revature.mcd.models;

public class Product {

    //region <attributes>
    private int id;
    private String name;
    //endregion

    //region <constructors>
    public Product(){ }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
    //endregion

}
