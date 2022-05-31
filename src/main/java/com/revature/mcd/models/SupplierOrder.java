package com.revature.mcd.models;

public class SupplierOrder {
    //region <attributes>
    private String id;
    private String supplier_id;
    private String order_id;
    //endregion

    //region <constructors>
    public SupplierOrder(){};

    public SupplierOrder(String id, String supplier_id, String order_id) {
        this.id = id;
        this.supplier_id = supplier_id;
        this.order_id = order_id;
    }

    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    //endregion
}
