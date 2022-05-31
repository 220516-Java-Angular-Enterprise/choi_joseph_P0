package com.revature.mcd.daos;

import com.revature.mcd.models.CartProduct;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartProductDAO implements CrudDAO<CartProduct> {
    Connection con = new DatabaseConnection().getCon();
    @Override
    public void save(CartProduct obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO cartproduct" +
                    "(id, cart_id, product_id) " +
                    "VALUES(?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getCart_id());
            ps.setString(3, obj.getProduct_id());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while adding an item to the cart.");
        }
    }

    @Override
    public void update(CartProduct obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public CartProduct getById(String id) {
        return null;
    }

    @Override
    public List<CartProduct> getAll() {
        return null;
    }

    public String getProductIdByCartID(String cart_id){
        String product_id ="";
        try{
            PreparedStatement ps = con.prepareStatement("SELECT product_id FROM cartproduct " +
                    "WHERE cart_id = ?");
            ps.setString(1, cart_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product_id = rs.getString("product_id");
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while getting a product_id by cart_id");
        }
        return product_id;
    }
}
