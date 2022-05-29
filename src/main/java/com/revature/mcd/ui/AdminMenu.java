package com.revature.mcd.ui;

import com.revature.mcd.models.Product;
import com.revature.mcd.models.User;
import com.revature.mcd.services.ProductService;
import com.revature.mcd.util.annotations.Inject;

import java.math.BigDecimal;
import java.util.Scanner;

public class AdminMenu implements IMenu{

    @Inject
    private final User user;
    @Inject
    private final ProductService productService;

    @Inject
    public AdminMenu(User user, ProductService productService){
        this.user = user;
        this.productService = productService;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        exit:
        {
            while(true){
                displayWelcomeMsg();
                System.out.print("Enter: ");
                String input = scanner.nextLine();
                switch(input){
                    case "1":
                        break exit;
                    case "2":
                        break exit;
                    case "3":
                        addToCatalog();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private void displayWelcomeMsg(){
        System.out.println("\nHello, MC & D Admin " + user.getUsername() + ".");
        System.out.println("[1] View Customers");
        System.out.println("[2] View Suppliers");
        System.out.println("[3] Add to Catalog");
        System.out.println("\n[x] Exit");
    }

    private void addToCatalog(){
        Scanner scanner = new Scanner(System.in);
        String id;
        String name;
        BigDecimal price;

        exit:{

            // enter product details
            while(true){
                System.out.println("\nEnter Product id: ");
                id = scanner.nextLine();

                System.out.println("\nEnter Product name: ");
                name = scanner.nextLine();

                while(true){
                    try{
                        System.out.println("\nEnter Product price: ");
                        price = new BigDecimal(scanner.nextLine());
                        break;
                    }catch(NumberFormatException e){
                        System.out.println("Please enter a valid price.");
                    }
                }

                confirmExit:{
                    while(true){
                        // confirm the product details
                        System.out.println("Confirm Product details (y/n/x)");
                        System.out.println("\nid: " + id);
                        System.out.println("name: " + name);
                        System.out.println("price: " + price);

                        System.out.print("\nEnter: ");
                        String input = scanner.nextLine();

                        // switch statement for user confirmation input: yes, no, or exit
                        switch(input){
                            case "y":
                                Product product = new Product(id, name, price);
                                productService.register(product);
                                System.out.println("New Product successfully registered.");
                                break exit;
                            case "n":
                                break confirmExit;
                            case "x":
                                break exit;
                            default:
                                System.out.println("Invalid Input.");
                                break;
                        }
                    }
                }
            }

        }
    }
}
