package com.revature.mcd.daos;

import com.revature.mcd.models.Product;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product> {
    Connection con = new DatabaseConnection().getCon();

    //region <user manipulation: save, update, delete>
    @Override
    public void save(Product obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " +
                    "products(id, productName, price)" +
                    "VALUES(?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setDouble(3, obj.getPrice().doubleValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while trying to save to the database");
        }
    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }
    //endregion

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product(rs.getString("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"));

                products.add(product);
            }
        }catch(SQLException e){
            throw new RuntimeException("An error occurred while retrieving product information from the database.");
        }
        return products;
    }
}
