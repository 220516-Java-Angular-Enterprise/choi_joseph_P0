package com.revature.mcd.ui;

import com.revature.mcd.models.User;
import com.revature.mcd.util.annotations.Inject;

import java.util.Scanner;

public class MainMenu implements IMenu {
    @Inject
    private final User user;

    @Inject
    public MainMenu(User user) {
        this.user = user;
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
                        break exit;
                    case "2":
                        break exit;
                    case "3":
                        break exit;
                    case "4":
                        break exit;
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

    private void displayWelcomeMsg(){
        System.out.println("\nWelcome to the Main Menu, " + user.getUsername() + ".");
        System.out.println("\n[1] Browse Products");
        System.out.println("[2] View Cart");
        System.out.println("[3] Update Account Details");
        System.out.println("[4] View Order History");
        System.out.println("[x] Exit to Log-in Menu");
    }

    private void browseProducts(){
        System.out.println("\nBrowse our excellent selection of anomalous items!");
    }
}
