package com.revature.mcd.daos;

import com.revature.mcd.models.Supplier;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements CrudDAO<Supplier>{

    Connection con = new DatabaseConnection().getCon();
    @Override
    public void save(Supplier obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO suppliers(id, supplierName) " +
                    "VALUES(?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getSupplierName());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to save a new supplier in the database.");
        }


    }

    @Override
    public void update(Supplier obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Supplier getById(String id) {
        return null;
    }

    public Supplier getByName(String name){
        Supplier supplier = new Supplier();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM suppliers " +
                    "WHERE supplierName = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                supplier.setId(rs.getString("id"));
                supplier.setSupplierName(rs.getString("supplierName"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to get supplier by name.");
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM suppliers");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Supplier supplier = new Supplier(rs.getString("id"), rs.getString("supplierName"));
                suppliers.add(supplier);
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while getting all suppliers from the database.");
        }
        return suppliers;
    }
}
