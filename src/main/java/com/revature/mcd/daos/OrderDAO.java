package com.revature.mcd.daos;

import com.revature.mcd.models.Order;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    Connection con = new DatabaseConnection().getCon();

    @Override
    public void save(Order obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders" +
                    "(id, orderDate, orderCost, user_id) " +
                    "VALUES(?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setDate(2, obj.getOrderDate());
            ps.setBigDecimal(3, obj.getOrderCost());
            ps.setString(4, obj.getUser_id());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while adding a supplierorder to the database.");
        }
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
