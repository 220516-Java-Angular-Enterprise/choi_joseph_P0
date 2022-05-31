package com.revature.mcd.daos;

import com.revature.mcd.models.Cart;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements CrudDAO<Cart>{
    Connection con = new DatabaseConnection().getCon();

    @Override
    public void save(Cart obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO cart(id, quantity, totalItemCost, user_id) " +
                    "VALUES(?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setInt(2, obj.getQuantity());
            ps.setBigDecimal(3, obj.getTotalItemCost());
            ps.setString(4, obj.getUser_id());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while adding an item to the cart.");
        }
    }

    @Override
    public void update(Cart obj) {

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM cart " +
                    "WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while deleting items from the cart.");
        }
    }

    @Override
    public Cart getById(String id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    public List<Cart> getUserCart(String user_id){
        List<Cart> cart = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cart " +
                    "WHERE user_id = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Cart cartItem = new Cart(rs.getString("id"), rs.getInt("quantity"),
                        rs.getBigDecimal("totalItemCost"), rs.getString("user_id"));
                cart.add(cartItem);
            }

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to retrieve a user's cart.");
        }
        return cart;
    }
}
