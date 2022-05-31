package com.revature.mcd.ui;

import com.revature.mcd.daos.ProductDAO;
import com.revature.mcd.daos.SupplierDAO;
import com.revature.mcd.daos.SupplierOrderDAO;
import com.revature.mcd.models.Location;
import com.revature.mcd.models.Order;
import com.revature.mcd.models.User;
import com.revature.mcd.services.*;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminMenu implements IMenu{

    @Inject
    private final User user;
    private final UserService userService;
    private final LocationService locationService;
    private final OrderService orderService;

    @Inject
    public AdminMenu(User user, UserService userService, LocationService locationService, OrderService orderService){
        this.user = user;
        this.userService = userService;
        this.locationService = locationService;
        this.orderService = orderService;
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
                        new SupplierMenu(userService, new SupplierService(new SupplierDAO()),
                                new ProductService(new ProductDAO()), orderService,
                                new SupplierOrderService(new SupplierOrderDAO())).start();
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
        System.out.println("[x] Exit");
    }

    private void displayCustomerMenuOptions(User user, Location location){
        System.out.println("\nCustomer Details:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Clearance Level: " + user.getClearanceLevel());
        System.out.println("Country of Residence: " + location.getCountry());
        System.out.println("City of Residence: " + location.getCity());

        System.out.println("\n[1] Change User Password");
        System.out.println("[2] Change User Clearance Level");
        System.out.println("[3] Change Residence");
        System.out.println("[4] View User Order History");
        System.out.println("[x] Exit");
    }

    //endregion

    public void displayCustomerMenu(User user){
        if(!user.equals(null)){
            exit:{
                while(true){
                    Location location = locationService.getLocation(user);
                    displayCustomerMenuOptions(user, location);
                    System.out.print("\nEnter: ");
                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    while(true){
                        switch(input){
                            case "1":
                                if(this.user.getClearanceLevel() > user.getClearanceLevel()){
                                    System.out.print("\nEnter new password: ");
                                    String password = scanner.nextLine();
                                    user.setPassword(password);
                                    userService.changeUserInfo(user);

                                    System.out.println("\nNew Password set to: " + user.getPassword());
                                    break exit;
                                }
                                else{
                                    System.out.println("Access denied: Insufficient clearance.");
                                    break exit;
                                }
                            case "2":
                                if(this.user.getClearanceLevel() > user.getClearanceLevel()){
                                    System.out.print("\nEnter new Clearance Level: ");
                                    int clearanceLevel = Integer.parseInt(scanner.nextLine());
                                    user.setClearanceLevel(clearanceLevel);
                                    userService.changeUserInfo(user);

                                    System.out.println("\nNew Clearance Level set to: " + user.getClearanceLevel());
                                    break exit;
                                }
                                else{
                                    System.out.println("Access denied: Insufficient clearance.");
                                    break exit;
                                }
                            case "3":
                                if(this.user.getClearanceLevel() > user.getClearanceLevel()){
                                    String country;
                                    String city;
                                    System.out.print("\nEnter new Country of Residence: ");
                                    country = scanner.nextLine();
                                    System.out.print("\nEnter new City of Residence: ");
                                    city = scanner.nextLine();

                                    if (locationService.isExistingLocation(country, city)) {
                                        location = locationService.getLocation(country, city);
                                        user.setLocation_id(location.getId());
                                        userService.changeUserInfo(user);
                                    }
                                    else{
                                        location = new Location(UUID.randomUUID().toString(),
                                                country, city);
                                        locationService.addLocation(location);
                                        user.setLocation_id(location.getId());
                                        userService.changeUserInfo(user);
                                    }

                                    System.out.println("\nCountry of Residence set to: " + country);
                                    System.out.println("\nCity of Residence set to: " + city);
                                    break exit;
                                }
                                else{
                                    System.out.println("Access denied: Insufficient clearance.");
                                    break exit;
                                }
                            case "4":
                                viewCustomerOrderHistory(user);
                                break exit;
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
        String input = scanner.nextLine();

        User user = userService.findUserByUsername(input);
        try{
            displayCustomerMenu(user);
        } catch(NullPointerException e){
            System.out.println("A User with that username does not exist.");
        }
    }

    private void viewCustomerOrderHistory(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nViewing " + user.getUsername() +
                "'s Order History. Press \"x\" to exit.");
        List<Order> orders = orderService.getOrdersByUserID(user.getId());
        for(Order order: orders){
            System.out.println("\n" + order);
        }
        exit:{
            while(true){
                System.out.print("\nEnter: ");
                String input = scanner.nextLine();
                switch(input){
                    case "x":
                        break exit;
                    default:
                        System.out.println("Invalid input.");
                }
            }
        }
    }
}
