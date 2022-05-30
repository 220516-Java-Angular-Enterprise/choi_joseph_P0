package com.revature.mcd.models;

public class User {

    //region <attributes>
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int clearanceLevel;
    private String location_id;
    //endregion

    //region <constructors>
    public User() { }

    public User(String id, String username, String password, String firstName, String lastName,
                int clearanceLevel, String location_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clearanceLevel = clearanceLevel;
        this.location_id = location_id;
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

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    //endregion

    //region <methods>
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clearanceLevel=" + clearanceLevel +
                ", location_id='" + location_id + '\'' +
                '}';
    }
//endregion
}
