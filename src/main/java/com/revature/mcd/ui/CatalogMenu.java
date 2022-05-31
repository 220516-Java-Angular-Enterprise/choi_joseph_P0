package com.revature.mcd.ui;

import com.revature.mcd.models.Cart;
import com.revature.mcd.models.CartProduct;
import com.revature.mcd.models.Product;
import com.revature.mcd.models.User;
import com.revature.mcd.services.CartProductService;
import com.revature.mcd.services.CartService;
import com.revature.mcd.services.ProductService;
import com.revature.mcd.util.annotations.Inject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CatalogMenu implements IMenu{
    @Inject
    private final User user;
    private final ProductService productService;
    private final CartService cartService;
    private final CartProductService cartProductService;

    public CatalogMenu(User user, ProductService productService,
                       CartService cartService, CartProductService cartProductService){
        this.user = user;
        this.productService = productService;
        this.cartService = cartService;
        this.cartProductService = cartProductService;
    }
    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<Product> catalog = productService.getCatalog();
        exit:{
            while(true){
                System.out.println("\nBrowse our current selection of anomalous items!");
                for(Product p: catalog){
                    System.out.println(p + "\n");
                }
                showCatalogOptions();
                System.out.print("Enter: ");
                String input = scanner.nextLine();

                switch(input){
                    case "1":
                        addProductToCart(catalog);
                        break;
                    case "x":
                        break exit;
                }
            }
        }
    }

    //region <ui displays>
    public void showCatalogOptions(){
        System.out.println("\n[1] Place a Item in Cart");
        System.out.println("[x] Exit");
    }
    //endregion

    public void addProductToCart(List<Product> catalog){
        Scanner scanner = new Scanner(System.in);
        List<String> productIDs = new ArrayList<>();
        for(Product p: catalog){
            productIDs.add(p.getId());
        }
        exit:{
            while(true){
                System.out.print("\nEnter ID of Product to Add to Cart: ");
                String input = scanner.nextLine();
                try{
                    System.out.print("Enter quantity to add to cart: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    if(productIDs.contains(input)){
                        Product product = productService.getByID(input);
                        Cart cart = new Cart(UUID.randomUUID().toString(), quantity,
                                product.getPrice().multiply(BigDecimal.valueOf(quantity)), user.getId());
                        CartProduct cartProduct = new CartProduct(UUID.randomUUID().toString(),
                                cart.getId(), product.getId());
                        cartService.addToCart(cart);
                        cartProductService.add(cartProduct);
                        System.out.println("\nProduct " + product.getName() + " added to cart.");
                        break exit;
                    }
                    else{
                        System.out.println("Invalid input.");
                        break exit;
                    }
                } catch(NumberFormatException e){
                    System.out.println("Please enter a valid number.");
                    break exit;
                }

            }
        }
    }
}
