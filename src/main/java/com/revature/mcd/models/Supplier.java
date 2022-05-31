package com.revature.mcd.models;

public class Supplier {
    //region <attributes>
    private String id;
    private String supplierName;
    //endregion

    //region <constructors>
    public Supplier(){

    }

    public Supplier(String id, String supplierName){
        this.id = id;
        this.supplierName = supplierName;
    }
    //endregion

    //region <accessors and mutators>
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    //endregion


    @Override
    public String toString() {
        return supplierName;
    }
}
