package com.revature.mcd.ui;

import com.revature.mcd.daos.*;
import com.revature.mcd.models.Location;
import com.revature.mcd.models.Order;
import com.revature.mcd.models.User;
import com.revature.mcd.services.*;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
    @Inject
    private final User user;
    private final UserService userService;
    private final LocationService locationService;
    private final OrderService orderService;
    @Inject
    public MainMenu(User user, UserService userService, LocationService locationService, OrderService orderService) {
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
                switch (input){
                    case "1":
                        new CatalogMenu(user, new ProductService(new ProductDAO()),
                                new CartService(new CartDAO()), new CartProductService(new CartProductDAO())).start();
                        break;
                    case "2":
                        new CartMenu(user, new ProductService(new ProductDAO()),
                                new CartService(new CartDAO()), new CartProductService(new CartProductDAO()),
                                new OrderService(new OrderDAO()), new SupplierOrderService(new SupplierOrderDAO())).start();
                        break;
                    case "3":
                        updateInfo();
                        break;
                    case "4":
                        viewOrderHistory();
                        break;
                    case "x":
                        System.out.println("\nThank you for shopping with MC & D!");
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
        System.out.println("\nWelcome to the Main Menu, " + user.getUsername() + ".");
        System.out.println("\n[1] Browse Products");
        System.out.println("[2] View Cart");
        System.out.println("[3] Update Account Details");
        System.out.println("[4] View Order History");
        System.out.println("[x] Exit to Log-in Menu");
    }

    private void displayUpdateDetailsMsg(Location location){
        System.out.println("\nUser Details:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Country of Residence: " + location.getCountry());
        System.out.println("City of Residence: " + location.getCity());

        System.out.println("\n[1] Change User Password");
        System.out.println("[2] Change Residence");
        System.out.println("[x] Exit");
    }
    //endregion

    private void updateInfo(){
        exit:{
            while(true){
                Location location = locationService.getLocation(user);
                displayUpdateDetailsMsg(location);
                System.out.print("\nEnter: ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                while(true){
                    switch(input){
                        case "1":
                            System.out.print("\nEnter new password: ");
                            String password = scanner.nextLine();
                            user.setPassword(password);
                            userService.changeUserInfo(user);

                            System.out.println("\nNew Password set to: " + user.getPassword());
                            break exit;
                        case "2":
                            String country;
                            String city;
                            System.out.print("\nEnter new Country of Residence: ");
                            country = scanner.nextLine();
                            System.out.print("Enter new City of Residence: ");
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
                            System.out.println("City of Residence set to: " + city);
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

    private void viewOrderHistory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nViewing Order History. Press \"x\" to exit.");
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
