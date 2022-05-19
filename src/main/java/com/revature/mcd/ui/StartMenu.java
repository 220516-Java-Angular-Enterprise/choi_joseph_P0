package com.revature.mcd.ui;

import com.revature.mcd.models.User;
import com.revature.mcd.services.UserService;
import com.revature.mcd.util.annotations.Inject;
import com.revature.mcd.util.custom_exceptions.InvalidUserException;

import java.util.Scanner;
import java.util.UUID;

/* This class purpose is to ask users to login, signup, or exit. */
public class StartMenu implements IMenu {

    /* We are injecting this StartMenu class with the UserService dependency. */
    /* Why? Dependency or dependent means relying on something for support. */
    /* In this case we are relying on our userService class to retrieve data's from the database, and validate username, password etc. */
    /* This is why we are using dependency injection. */
    @Inject
    private final UserService userService;

    @Inject
    public StartMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {
        /* For user input */
        Scanner scan = new Scanner(System.in);

        /* Break label. Basically breaks out of the switch statement and while loop in one go. */
        exit:
        {
            while (true) {
                displayWelcomeMsg();

                /* Asking user to enter in their input. */
                System.out.print("\nEnter: ");
                String input = scan.nextLine();

                /* Switch case, basically if else statement but more simple. */
                switch (input) {
                    /* If the user enters 1, 2, or x. */
                    case "1":
                        /* Call the login() method. */
                        login();
                        break;
                    case "2":
                        /* Call the signup() method. */
                        signup();
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        /* Breaking out of everything. */
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }

    private void displayWelcomeMsg() {
        /* Welcome message. */
        System.out.println("\nWelcome to Yolp!");
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
    }

    private void login() {
        String username;
        String password;
        User user = new User();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\nLogging in...");
            System.out.print("\nUsername: ");
            username = scan.nextLine();

            System.out.print("\nPassword: ");
            password = scan.nextLine();

            try {
                user = userService.login(username, password);

                if (user.getRole().equals("ADMIN")) new AdminMenu().start();
                else new MainMenu(user).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void signup() {
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        completeExit:
        {
            while (true) {
                System.out.println("\nCreating account...");

                while (true) {
                    /* Asking user to enter in username. */
                    System.out.print("\nUsername: ");
                    username = scan.nextLine();

                    /* If the username is valid break out of the loop. Else re-enter username. */
                    try {
                        if (userService.isValidUsername(username)) {
                            if (userService.isNotDuplicateUsername(username)) break;
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());
                    }
                }


                while (true) {
                    /* Asking user to enter in password. */
                    System.out.print("\nPassword: ");
                    password = scan.nextLine();

                    /* If the password is valid confirm the password again. Else re-enter password. */
                    try {
                        if (userService.isValidPassword(password)) {
                            /* Asking user to enter in password again. */
                            System.out.print("\nRe enter password again: ");
                            String confirm = scan.nextLine();

                            /* If the two password equals each other, break. Else re-enter password. */
                            if (password.equals(confirm)) break;
                            else System.out.println("Password does not match :(");
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());
                    }
                }

                confirmExit:
                {
                    while (true) {
                        /* Asking user to confirm username and password. */
                        System.out.println("\nPlease confirm your credentials (y/n)");
                        System.out.println("\nUsername: " + username);
                        System.out.println("Password: " + password);

                        System.out.print("\nEnter: ");
                        String input = scan.nextLine();

                        /* Switch statement for user input. Basically yes or no. */
                        switch (input) {
                            case "y":
                                /* If yes, we instantiate a User object to store all the information into it. */
                                User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT");

                                userService.register(user);

                                /* Calling the anonymous class MainMenu.start() to navigate to the main menu screen. */
                                /* We are also passing in a user object, so we know who is logged in. */
                                new MainMenu(user).start();

                                /* Break out of the entire loop. */
                                break completeExit;
                            case "n":
                                /* Re-enter in credentials again. */
                                break confirmExit;
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
