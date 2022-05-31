package com.revature.mcd.daos;

import com.revature.mcd.models.Order;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
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
        Order order = new Order();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders " +
                    "WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                order.setId(rs.getString("id"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setOrderCost(rs.getBigDecimal("orderCost"));
                order.setUser_id(rs.getString("user_id"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to get order by ID.");
        }
        return order;
    }

    @Override
    public List getAll() {
        return null;
    }

    public List<Order> getOrdersByUserID(String id){
        List<Order> orders = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders " +
                    "WHERE user_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getString("id"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setOrderCost(rs.getBigDecimal("orderCost"));
                order.setUser_id(rs.getString("user_id"));

                orders.add(order);
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to get a user's order history.");
        }
        return orders;
    }
}
