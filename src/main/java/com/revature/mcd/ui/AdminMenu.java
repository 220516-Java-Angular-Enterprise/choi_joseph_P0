package com.revature.mcd.ui;

import com.revature.mcd.models.Product;
import com.revature.mcd.models.User;
import com.revature.mcd.services.ProductService;
import com.revature.mcd.services.UserService;
import com.revature.mcd.util.annotations.Inject;

import java.math.BigDecimal;
import java.util.Scanner;

public class AdminMenu implements IMenu{

    @Inject
    private final User user;
    @Inject
    private final UserService userService;
    @Inject
    private final ProductService productService;

    @Inject
    public AdminMenu(User user, UserService userService, ProductService productService){
        this.user = user;
        this.userService = userService;
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
                        findCustomer();
                        break;
                    case "2":
                        break;
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

    //region <ui displays>
    private void displayWelcomeMsg(){
        System.out.println("\nHello, MC & D Admin " + user.getUsername() + ".");
        System.out.println("[1] Find Customer by Username");
        System.out.println("[2] View Suppliers");
        System.out.println("[3] Add to Catalog");
        System.out.println("[x] Exit");
    }

    private void displayCustomerMenuOptions(User user){
        System.out.println("\nCustomer Details:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Clearance Level: " + user.getClearanceLevel());

        System.out.println("\n[1] Change User Password");
        System.out.println("[2] Change User Clearance Level");
        System.out.println("[3] View User Order History");
        System.out.println("[x] Exit");
    }
    //endregion

    public void displayCustomerMenu(User searchedUser){
        if(!searchedUser.equals(null)){
            exit:{
                while(true){
                    displayCustomerMenuOptions(searchedUser);
                    System.out.print("\nEnter: ");
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    while(true){
                        switch(input){
                            case "1":
                                if(this.user.getClearanceLevel() > searchedUser.getClearanceLevel()){
                                    System.out.print("\nEnter new password: ");
                                    String password = scanner.nextLine();
                                    searchedUser.setPassword(password);
                                    userService.changeUserInfo(searchedUser);

                                    System.out.println("\nNew Password set to: " + searchedUser.getPassword());
                                    break exit;
                                }
                                else{
                                    System.out.println("Access denied: Insufficient clearance.");
                                    break exit;
                                }
                            case "2":
                                if(this.user.getClearanceLevel() > searchedUser.getClearanceLevel()){
                                    System.out.print("\nEnter new Clearance Level: ");
                                    int clearanceLevel = Integer.parseInt(scanner.nextLine());
                                    searchedUser.setClearanceLevel(clearanceLevel);
                                    userService.changeUserInfo(searchedUser);

                                    System.out.println("\nNew Clearance Level set to: " + searchedUser.getClearanceLevel());
                                    break exit;
                                }
                                else{
                                    System.out.println("Access denied: Insufficient clearance.");
                                    break exit;
                                }
                            case "3":
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
        }
    }

    // finds customer by username
    private void findCustomer(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter customer's username: ");
        String search = scanner.nextLine();

        User searchedUser = userService.findUserByUsername(search);
        try{
            displayCustomerMenu(searchedUser);
        } catch(NullPointerException e){
            System.out.println("A User with that username does not exist.");
        }
    }

    // register new product to catalog
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
