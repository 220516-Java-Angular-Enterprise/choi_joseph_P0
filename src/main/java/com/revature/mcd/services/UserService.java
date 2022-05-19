package com.revature.mcd.services;

import com.revature.mcd.daos.UserDAO;
import com.revature.mcd.models.User;
import com.revature.mcd.util.annotations.Inject;
import com.revature.mcd.util.custom_exceptions.InvalidUserException;

import java.util.List;

/* Purpose: validation ie. checks username, password, and retrieve data from our daos. */
public class UserService {

    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (isValidCredentials(user)) return user;

        return null;
    }

    public void register(User user) {
        userDAO.save(user);
    }

    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;

        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }

    public boolean isNotDuplicateUsername(String username) {
        List<String> usernames = userDAO.getAllUsernames();

        if (usernames.contains(username)) throw new InvalidUserException("Username is already taken :(");

        return true;
    }

    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;

        throw new InvalidUserException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
    }

    private boolean isValidCredentials(User user) {

        if (user.getUsername() == null && user.getPassword() == null) throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return true;
    }
}
