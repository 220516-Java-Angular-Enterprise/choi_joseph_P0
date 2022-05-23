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
                    default:
                        break;
                }
            }

        }
    }

    private void displayWelcomeMsg(){
        System.out.println("\nWelcome to the Main Menu, " + user.getUsername() + ".");
        System.out.println("\n[1] Browse Products");
        System.out.println("[2] Select Marshall, Carter & Dark sites");
    }
}
