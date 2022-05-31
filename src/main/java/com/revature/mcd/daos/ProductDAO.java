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
                    "products(id, productName, price, stock, description, supplier_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setBigDecimal(3, obj.getPrice());
            ps.setInt(4, obj.getStock());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getSupplier_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while trying to save a product to the database");
        }
    }

    @Override
    public void update(Product obj) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE products " +
                    "SET productName = ?, price = ?, stock = ?, description = ?, supplier_id = ? " +
                    "WHERE id = ?");
            ps.setString(1, obj.getName());
            ps.setDouble(2, obj.getPrice().doubleValue());
            ps.setInt(3, obj.getStock());
            ps.setString(4, obj.getDescription());
            ps.setString(5, obj.getSupplier_id());
            ps.setString(6, obj.getId());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to update a product in the database.");
        }
    }

    @Override
    public void delete(String id) {

    }
    //endregion

    @Override
    public Product getById(String id) {
        Product product = new Product();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products " +
                    "WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product.setId(rs.getString("id"));
                product.setName(rs.getString("productName"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
                product.setSupplier_id(rs.getString("supplier_id"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while getting a Product by ID from the database.");
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product(rs.getString("id"), rs.getString("productName"),
                        rs.getBigDecimal("price"), rs.getInt("stock"),
                        rs.getString("description"), rs.getString("supplier_id"));

                products.add(product);
            }
        }catch(SQLException e){
            throw new RuntimeException("An error occurred while retrieving product information from the database.");
        }
        return products;
    }

    public List<Product> getAllSupplierProduct(String supplier_id){
        List<Product> products = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products " +
                    "WHERE supplier_id = ?");
            ps.setString(1, supplier_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product(rs.getString("id"), rs.getString("productName"),
                        rs.getBigDecimal("price"), rs.getInt("stock"),
                        rs.getString("description"), rs.getString("supplier_id"));

                products.add(product);
            }
        }catch(SQLException e){
            throw new RuntimeException("An error occurred while retrieving product information from the database.");
        }
        return products;
    }
}
