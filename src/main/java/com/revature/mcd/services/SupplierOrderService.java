package com.revature.mcd.services;

import com.revature.mcd.daos.SupplierOrderDAO;
import com.revature.mcd.models.SupplierOrder;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;

public class SupplierOrderService {
    @Inject
    private final SupplierOrderDAO supplierOrderDAO;

    public SupplierOrderService(SupplierOrderDAO supplierOrderDAO){
        this.supplierOrderDAO = supplierOrderDAO;
    }

    public void add(SupplierOrder supplierOrder) {
        supplierOrderDAO.save(supplierOrder);
    }

    public List<SupplierOrder> getAllBySupplierID(String id) {
        return supplierOrderDAO.getAllBySupplierID(id);
    }
}
