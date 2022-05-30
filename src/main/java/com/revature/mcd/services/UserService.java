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

    // service to log-in and return valid credentials, returns null otherwise
    public User loginService(String username, String password) {
        List<User> users = userDAO.getAll();
        User user = new User();

        for(User u: users){
            if(u.getUsername().equals(username)){
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                user.setFirstName(u.getFirstName());
                user.setLastName(u.getLastName());
                user.setClearanceLevel(u.getClearanceLevel());

                if(u.getPassword().equals(password)){
                    user.setPassword(u.getPassword());
                    break;
                }
            }
        }

        return isValidCredentials(user);
    }

    // service to register new users in database
    public void register(User user) {
        userDAO.save(user);
    }

    // service for username validation based on specified regex
    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;

        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }

    // service to check duplicate usernames
    public boolean isNotDuplicateUsername(String username) {
        List<String> usernames = userDAO.getAllUsernames();

        if (usernames.contains(username)) throw new InvalidUserException("Username is already taken.");

        return true;
    }

    // service for password validation based on specified regex
    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;

        throw new InvalidUserException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
    }

    // service to find existing user in database by username
    public User findUserByUsername(String username){
        List<User> users = userDAO.getAll();
        User user = new User();
        for(User u: users){
            if(u.getUsername().equals(username)){
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                user.setPassword(u.getPassword());
                user.setFirstName(u.getFirstName());
                user.setLastName(u.getLastName());
                user.setClearanceLevel(u.getClearanceLevel());
                return user;
            }
        }
        return null;
    }

    public void changeUserInfo(User user){
        userDAO.update(user);
    }

    // service for exceptions involving invalid credentials
    private User isValidCredentials(User user) {

        if (user.getUsername() == null)
            throw new InvalidUserException("\nUsername does not exist.");
        else if (user.getPassword() == null)
            throw new InvalidUserException("\nPassword is incorrect. Please try again.");

        return user;
    }
}
