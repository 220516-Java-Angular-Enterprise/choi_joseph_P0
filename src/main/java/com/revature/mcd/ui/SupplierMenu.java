package com.revature.mcd.ui;

import com.revature.mcd.models.*;
import com.revature.mcd.services.*;
import com.revature.mcd.util.annotations.Inject;

import java.math.BigDecimal;
import java.util.*;

public class SupplierMenu implements IMenu{
    @Inject
    private final UserService userService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final OrderService orderService;
    private final SupplierOrderService supplierOrderService;
    public SupplierMenu(UserService userService, SupplierService supplierService, ProductService productService,
                        OrderService orderService, SupplierOrderService supplierOrderService){
        this.userService = userService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.orderService = orderService;
        this.supplierOrderService = supplierOrderService;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        exit:
        {
            while(true){
                List<Supplier> suppliers = supplierService.getAll();
                System.out.println("\nSuppliers: ");
                for(Supplier s: suppliers){
                    System.out.println(s);
                }
                displayInitialMsg();
                System.out.print("Enter: ");
                String input = scanner.nextLine();
                switch(input){
                    case "1":
                        Supplier supplier = findSupplier();
                        if(supplier.getSupplierName() != null){
                                displaySupplierMenu(supplier);
                            }
                        else{
                            System.out.println("A supplier with that name does not exist.");
                        }
                        break;
                    case "2":
                        addSupplier();
                        System.out.println("New supplier successfully registered.");
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
    private void displayInitialMsg(){
        System.out.println("\n[1] View Existing Supplier");
        System.out.println("[2] Add New Supplier");
        System.out.println("[x] Exit");
    }

    private void displaySupplierMenu(Supplier supplier){
        exit:{
            System.out.println("\nNow Viewing Supplier " + supplier.getSupplierName());
            System.out.println("\n[1] Update Inventory");
            System.out.println("[2] View Order History");
            System.out.println("[x] Exit");

            System.out.print("Enter: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            while(true){
                switch(input){
                    case "1":
                        updateInventory(supplier);
                        break exit;
                    case "2":
                        viewOrderHistory(supplier);
                    case "x":
                        break exit;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
            }
        }
    }

    //endregion

    private Supplier findSupplier(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter Supplier's Name: ");
        String input = scanner.nextLine();
        return supplierService.findSupplierByName(input);
    }

    private void addSupplier(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter New Supplier's Name or \"x\" to exit: ");
        String input = scanner.nextLine();

        if(!input.equals("x")){
            Supplier supplier = new Supplier(UUID.randomUUID().toString(), input);
            supplierService.registerSupplierService(supplier);
        }
    }

    private void updateInventory(Supplier supplier){
        List<Product> products = productService.getInventory(supplier.getId());
        Scanner scanner = new Scanner(System.in);

        if(!products.isEmpty()){
            System.out.println("\n" + supplier.getSupplierName() + "'s Inventory: \n");
            for(Product product: products){
                System.out.println(product + "\n");
            }

            System.out.println("[1] Add New Product");
            System.out.println("[2] Change Stock of Existing Product");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            String input = scanner.nextLine();
            exit:{
                while(true){
                    switch(input){
                        case "1":
                            try{
                                System.out.print("Enter Product ID: ");
                                String id = scanner.nextLine();
                                System.out.print("Enter Product Name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter Product Price: ");
                                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
                                while(price.compareTo(BigDecimal.valueOf(0)) < 0){
                                    System.out.print("Stock must be 0 or Greater: ");
                                    price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
                                }
                                System.out.print("Enter Product Stock: ");
                                int stock = Integer.parseInt(scanner.nextLine());
                                while(stock < 0){
                                    System.out.print("Stock must be 0 or Greater: ");
                                    stock = Integer.parseInt(scanner.nextLine());
                                }
                                System.out.print("Enter Product Description: ");
                                String description = scanner.nextLine();
                                Product product = new Product(id, name, price, stock, description, supplier.getId());
                                productService.register(product);
                                System.out.println("New product " + product.getName() + " registered.");
                                break exit;
                            } catch(NumberFormatException e){
                                System.out.println("An error occurred due to invalid input.");
                                break;
                            }

                        case "2":
                            System.out.print("Enter Product ID: ");
                            Product product = new Product();
                            String id = scanner.nextLine();

                            for(Product p: products){
                                if(p.getId().equals(id)){
                                    product = p;
                                    break;
                                }
                            }
                            if(product.getId() != null){
                                System.out.print("Enter new stock for " + product.getName() + ": ");
                                stockExit:{
                                    while(true){
                                        try{
                                            int stock = Integer.parseInt(scanner.nextLine());
                                            while(stock < 0){
                                                System.out.print("Stock must be 0 or Greater: ");
                                                stock = Integer.parseInt(scanner.nextLine());
                                            }
                                            product.setStock(stock);
                                            productService.updateInventory(product);
                                            System.out.println("Stock of " + product.getId() + " updated.");
                                            break stockExit;
                                        } catch(NumberFormatException e){
                                            System.out.println("Invalid input.");
                                            break;
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("Product with that ID does not exist.");
                            }
                        case "x":
                            break exit;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                }
            }
        }
        else{
            System.out.println("\nInventory is empty.");
            System.out.println("\n[1] Add New Product");
            System.out.println("[x] Exit");

            System.out.print("Enter: ");
            String input = scanner.nextLine();
            addExit:{
                while(true){
                    switch(input){
                        case "1":
                            try{
                                System.out.print("Enter Product ID: ");
                                String id = scanner.nextLine();
                                System.out.print("Enter Product Name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter Product Price: ");
                                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
                                System.out.print("Enter Product Stock: ");
                                int stock = Integer.parseInt(scanner.nextLine());
                                System.out.print("Enter Product Description: ");
                                String description = scanner.nextLine();
                                Product product = new Product(id, name, price, stock, description, supplier.getId());
                                productService.register(product);
                                System.out.println("New Product " + product.getName() + " added to catalog.");
                                break addExit;
                            } catch(NumberFormatException e){
                                System.out.println("An error occurred due to invalid input.");
                                break;
                            }
                        case "x":
                            break addExit;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                }
            }
        }

    }

    private void viewOrderHistory(Supplier supplier) {
        Scanner scanner = new Scanner(System.in);
        List<SupplierOrder> supplierorders = supplierOrderService.getAllBySupplierID(supplier.getId());
        List<Order> orders = new ArrayList<>();

        for(SupplierOrder so: supplierorders){
            orders.add(orderService.getOrderByID(so.getOrder_id()));
        }

        sortExit:{
            while(true){
                System.out.println("\nSort By: ");
                System.out.println("[1] Date");
                System.out.println("[2] Cost");
                System.out.print("Enter: ");
                String input = scanner.nextLine();
                switch(input){
                    case "1":
                        orders.sort(new DateComparator());
                        break sortExit;
                    case "2":
                        orders.sort(new CostComparator());
                        break sortExit;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
            }
        }

        System.out.println("\nViewing " + supplier.getSupplierName() + "'s Order History: ");
        for(Order order: orders){
            System.out.println("\nUser: " + userService.getByID(order.getUser_id()).getUsername());
            System.out.println(order);
        }
        exit:{
            while(true){
                System.out.println("Enter \"x\" to exit.");
                System.out.print("Enter: ");
                String input = scanner.nextLine();
                switch(input){
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
