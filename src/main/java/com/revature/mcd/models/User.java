package com.revature.mcd.models;

public class User {

    //region <attributes>
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int clearanceLevel;
    //endregion

    //region <constructors>
    public User() { }

    public User(String id, String username, String password, String firstName, String lastName, int clearanceLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clearanceLevel = clearanceLevel;
    }
    //endregion

    //region <accessors and mutators>
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }
    //endregion

    //region <methods>
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + clearanceLevel + '\'' +
                '}';
    }
    //endregion
}
