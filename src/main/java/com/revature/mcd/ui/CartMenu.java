package com.revature.mcd.ui;

import com.revature.mcd.models.*;
import com.revature.mcd.services.*;
import com.revature.mcd.util.annotations.Inject;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CartMenu implements IMenu{
    @Inject
    private final User user;
    private final ProductService productService;
    private final CartService cartService;
    private final CartProductService cartProductService;
    private final OrderService orderService;
    private final SupplierOrderService supplierOrderService;
    public CartMenu(User user, ProductService productService,
                    CartService cartService, CartProductService cartProductService,
                    OrderService orderService, SupplierOrderService supplierOrderService) {
        this.user = user;
        this.productService = productService;
        this.cartService = cartService;
        this.cartProductService = cartProductService;
        this.orderService = orderService;
        this.supplierOrderService = supplierOrderService;
    }

    @Override
    public void start() {
        List<Cart> cartItems = cartService.getCart(user.getId());
        Scanner scanner = new Scanner(System.in);
        if(cartItems.isEmpty()){
            System.out.println("\nThere are no items in the cart.");
        }
        else{
            exit:{
                while(true){
                    System.out.println("\nItems in Cart: ");
                    BigDecimal orderTotal = BigDecimal.valueOf(0);
                    for(Cart c: cartItems){
                        System.out.println("\nProduct Name: " +
                                productService.getByID(cartProductService.getProductIdByCartID(c.getId())).getName());
                        System.out.println("Item Price: " +
                                productService.getByID(cartProductService.getProductIdByCartID(c.getId())).getPrice());
                        System.out.println("Quantity: " + c.getQuantity());
                        System.out.println("Total: " + c.getTotalItemCost());
                        orderTotal = orderTotal.add(c.getTotalItemCost());
                    }
                    System.out.println("\nOrder Total: " + orderTotal);
                    System.out.println("\n[1] Buy");
                    System.out.println("[2] Empty Cart");
                    System.out.println("[x] Exit");
                    String input = scanner.nextLine();

                    switch(input){
                        case "1":
                            System.out.println(makePurchase(cartItems, orderTotal));
                            break exit;
                        case "2":
                            for(Cart c: cartItems){
                                cartService.remove(c.getId());
                            }
                            System.out.println("\nCart successfully emptied.");
                            break exit;
                        case "x":
                            break exit;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                }
            }
        }
    }

    private String makePurchase(List<Cart> cartItems, BigDecimal orderTotal) {
        Scanner scanner = new Scanner(System.in);

        boolean canFulfillOrder = true;
        for(Cart c: cartItems){
            if(!productService.inStock(productService.getByID(cartProductService.getProductIdByCartID(c.getId())),
                    c.getQuantity())){
                canFulfillOrder = false;
            }
        }

        if (canFulfillOrder) {
            try{
                System.out.print("\nEnter Date (yyyy-mm-dd): ");
                Date orderDate = Date.valueOf(scanner.nextLine());

                Order order = new Order(UUID.randomUUID().toString(), orderDate, orderTotal, user.getId());
                orderService.add(order);

                for(Cart c: cartItems){
                    SupplierOrder supplierOrder = new SupplierOrder(UUID.randomUUID().toString(),
                            productService.getByID(cartProductService.getProductIdByCartID(c.getId())).getSupplier_id(),
                            order.getId());
                    supplierOrderService.add(supplierOrder);

                    Product product = productService.getByID(cartProductService.getProductIdByCartID(c.getId()));
                    product.setStock(product.getStock() - c.getQuantity());
                    productService.updateInventory(product);
                    cartService.remove(c.getId());
                }
                return "Purchase Successful.";

            } catch(IllegalArgumentException e){
                System.out.println("Invalid Input.");
            }
        }
        return "Order cannot be fulfilled. Please empty cart and try again.";
    }
}
