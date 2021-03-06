package com.revature.mcd.services;

import com.revature.mcd.daos.SupplierDAO;
import com.revature.mcd.models.Supplier;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;

public class SupplierService {
    @Inject
    private final SupplierDAO supplierDAO;
    public SupplierService(SupplierDAO supplierDAO){
        this.supplierDAO = supplierDAO;
    }

    public List<Supplier> getAll(){
        return supplierDAO.getAll();
    }

    public void registerSupplierService(Supplier supplier){
        supplierDAO.save(supplier);
    }

    public Supplier findSupplierByName(String supplierName) {
        return supplierDAO.getByName(supplierName);
    }

}
