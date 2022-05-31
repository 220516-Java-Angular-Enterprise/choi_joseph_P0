package com.revature.mcd.services;

import com.revature.mcd.daos.ProductDAO;
import com.revature.mcd.models.Product;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;

public class ProductService {
    @Inject
    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    // service to get the inventory(all products) for a given parameter supplier_id
    public List<Product> getInventory(String supplier_id){
        return productDAO.getAllSupplierProduct(supplier_id);
    }

    public void updateInventory(Product product){
        productDAO.update(product);
    }

    // service to get all products
    public List<Product> getCatalog(){
        return productDAO.getAll();
    }

    // service to get a product by id
    public Product getByID(String id){
        return productDAO.getById(id);
    }

    // service to register new product in database
    public void register(Product product) {
        productDAO.save(product);
    }

    // service to check if product is in stock for an order
    public boolean inStock(Product product, int quantity){
        if(product.getStock() >= quantity){
            return true;
        }
        else{
            return false;
        }
    }
}
