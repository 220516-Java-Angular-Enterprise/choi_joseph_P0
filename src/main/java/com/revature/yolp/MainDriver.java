package com.revature.yolp;

import com.revature.yolp.services.UserService;
import com.revature.yolp.ui.StartMenu;

/* This class purpose is to start our application. */
public class MainDriver {
    public static void main(String[] args) {
        UserService userService = new UserService();

        /* anonymous function. */
        /* This anonymous function will disappear after the start method execute. */
        new StartMenu(userService).start();
    }
}
