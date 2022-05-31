package com.revature.mcd.daos;

import com.revature.mcd.models.SupplierOrder;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplierOrderDAO implements CrudDAO<SupplierOrder> {
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(SupplierOrder obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO supplierorder (id, order_id, supplier_id) " +
                    "VALUES(?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getOrder_id());
            ps.setString(3, obj.getSupplier_id());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while adding a supplierorder to the database.");
        }
    }

    @Override
    public void update(SupplierOrder obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public SupplierOrder getById(String id) {
        return null;
    }

    @Override
    public List<SupplierOrder> getAll() {
        return null;
    }
}
