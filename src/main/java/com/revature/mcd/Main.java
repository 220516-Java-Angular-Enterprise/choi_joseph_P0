package com.revature.mcd;

import com.revature.mcd.daos.UserDAO;
import com.revature.mcd.services.UserService;
import com.revature.mcd.ui.StartMenu;

/* This class purpose is to start our application. */
public class Main {
    public static void main(String[] args) {

        /* anonymous function. */
        /* This anonymous function will disappear after the start method is done executing. */
        new StartMenu(new UserService(new UserDAO())).start();
    }
}
