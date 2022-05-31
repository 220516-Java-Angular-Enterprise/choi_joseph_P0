package com.revature.mcd.services;

import com.revature.mcd.daos.ProductDAO;
import com.revature.mcd.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductService productService = new ProductService(new ProductDAO());

    @Test
    // returns a list of Products based on supplier_id
    void getInventory() {
        String id = "c2c8a9bc-99d8-47be-8b29-ec22b02c0943";
        String notValidID = UUID.randomUUID().toString();
        String multipleId = "61fcc896-a122-49ee-91d7-3c4c90e0e76b";

        List<Product> firstAssertProducts = productService.getInventory(id);
        List<Product> secondAssertProducts = productService.getInventory(notValidID);
        List<Product> thirdAssertProducts = productService.getInventory(multipleId);

        assertEquals(firstAssertProducts.get(0).getName(), "His Broken Heart");
        assertTrue(secondAssertProducts.isEmpty());
        assertEquals(thirdAssertProducts.size(), 2);
    }

    @Test
    // service to get all products
    void getCatalog() {
        assertFalse(productService.getCatalog().isEmpty());
        assertEquals(productService.getCatalog().get(0).getName(), "The Sculpture");
    }

    @Test
    // gets a product by id
    void getByID() {
        String validId = "SCP-173";
        String invalidId = UUID.randomUUID().toString();

        Product firstAssertProduct = productService.getByID(validId);
        Product secondAssertProduct = productService.getByID(invalidId);

        assertEquals(firstAssertProduct.getName(), "The Sculpture");
        assertNull(secondAssertProduct.getName());
    }

    @Test
    // service to check if product is in stock for a purchase
    void inStock() {
        Product product = new Product(UUID.randomUUID().toString(), "A name", BigDecimal.valueOf(99.99),
                8, "A Product.", UUID.randomUUID().toString());

        assertTrue(productService.inStock(product, 6));
        assertTrue(productService.inStock(product, 8));
        assertFalse(productService.inStock(product, 10));
    }
}