package com.revature.mcd.daos;

import com.revature.mcd.models.Order;
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
                    "users(id, userName, userPassword, firstName, lastName, securityLevel, location_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getFirstName());
            ps.setString(5, obj.getLastName());
            ps.setInt(6, obj.getClearanceLevel());
            ps.setString(7, obj.getLocation_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while trying to save to the database");
        }
    }

    @Override
    public void update(User obj) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE users " +
                    "SET userName = ?, userPassword = ?, firstName = ?, lastName = ?, " +
                    "securityLevel = ?, location_id = ? " +
                    "WHERE id = ?");
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getFirstName());
            ps.setString(4, obj.getLastName());
            ps.setInt(5, obj.getClearanceLevel());
            ps.setString(6, obj.getLocation_id());
            ps.setString(7, obj.getId());
            ps.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to update the database.");
        }
    }

    @Override
    public void delete(String id) {

    }
    //endregion

    //region <methods>
    @Override
    public User getById(String id) {
        User user = new User();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users " +
                    "WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("userPassword"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setClearanceLevel(rs.getInt("securityLevel"));
                user.setLocation_id(rs.getString("location_id"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to get order by ID.");
        }
        return user;
    }

    @Override
    // returns a List of all Users
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
                        rs.getInt("securityLevel"),
                        rs.getString("location_id")
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
