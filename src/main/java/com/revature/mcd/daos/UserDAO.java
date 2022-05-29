package com.revature.mcd.daos;

import com.revature.mcd.models.User;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    //region <user manipulation: save, update, delete>
    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO " +
                    "users(id, userName, userPassword, firstName, lastName, securityLevel)" +
                    "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getFirstName());
            ps.setString(5, obj.getLastName());
            ps.setInt(6, obj.getClearanceLevel());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while trying to save to the database");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }
    //endregion

    //region <methods>
    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("id"),
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("securityLevel")
                        );

                users.add(user);
            }

        } catch (SQLException e){
            throw new RuntimeException("An error occurred while trying to get all users' info from the database.");
        }
        return users;
    }

    // returns all usernames in database
    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT userName from users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                usernames.add(rs.getString("userName"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error while trying to get usernames from the database.");
        }
        return usernames;
    }

    //endregion
}
