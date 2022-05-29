package com.revature.mcd.services;

import com.revature.mcd.daos.ProductDAO;
import com.revature.mcd.models.Product;
import com.revature.mcd.models.User;
import com.revature.mcd.util.annotations.Inject;

public class ProductService {
    @Inject
    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    // service to register new product in database
    public void register(Product product) {
        productDAO.save(product);
    }

}
