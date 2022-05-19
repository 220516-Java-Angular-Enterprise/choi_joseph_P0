package com.revature.mcd;

import com.revature.mcd.daos.UserDAO;
import com.revature.mcd.services.UserService;
import com.revature.mcd.ui.StartMenu;

/* This class purpose is to start our application. */
public class MainDriver {
    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//        UserService userService = new UserService(userDAO);

        /* anonymous function. */
        /* This anonymous function will disappear after the start method is done executing. */
        new StartMenu(new UserService(new UserDAO())).start();
    }
}
