package com.revature.mcd.models;

public class Location {
    //region <attributes>
    private String id;
    private String country;
    private String city;
    //endregion

    //region <constructors
    public Location(){

    }

    public Location(String id, String country, String city){
        this.id = id;
        this.country = country;
        this.city = city;
    }
    //endregion

    //region <accessors and mutators>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //endregion
}
