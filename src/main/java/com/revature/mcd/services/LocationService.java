package com.revature.mcd.services;

import com.revature.mcd.daos.LocationDAO;
import com.revature.mcd.models.Location;
import com.revature.mcd.models.User;
import com.revature.mcd.util.annotations.Inject;
import java.util.List;

public class LocationService {
    @Inject
    private final LocationDAO locationDAO;

    @Inject
    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    // checks if location already exists in database
    public boolean isExistingLocation(String country, String city){
        List<Location> locations = locationDAO.getAll();
        for(Location loc: locations){
            if(loc.getCountry().equals(country) && loc.getCity().equals(city)){
                return true;
            }
        }
        return false;
    }

    //get location object by user
    public Location getLocation(User user){
        return locationDAO.getById(user.getLocation_id());
    }

    // get location object by country and city
    public Location getLocation(String country, String city){
        return locationDAO.getByCountryAndCity(country, city);
    }

    // add new location
    public void addLocation(Location location){
        locationDAO.save(location);
    }
}
