package com.revature.mcd.daos;

import com.revature.mcd.models.Location;
import com.revature.mcd.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements CrudDAO<Location> {

    Connection con = DatabaseConnection.getCon();

    //region <location manipulation: save, update, delete
    @Override
    public void save(Location obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO " +
                    "locations(id, country, city)" +
                    "VALUES(?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getCountry());
            ps.setString(3, obj.getCity());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while saving a new location.");
        }
    }

    @Override
    public void update(Location obj) {


    }

    @Override
    public void delete(String id) {

    }
    //endregion

    @Override
    public Location getById(String id) {
        return null;
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM locations");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Location location = new Location(rs.getString("id"),
                        rs.getString("country"),
                        rs.getString("city")
                );

                locations.add(location);
            }

        } catch (SQLException e){
            throw new RuntimeException("An error occurred while trying to get all locations from the database.");
        }
        return locations;
    }

    public Location getByCountryAndCity(String country, String city){
        Location location = new Location();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM locations " +
                    "WHERE country = ? AND city = ?");
            ps.setString(1, country);
            ps.setString(2, city);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                location.setId(rs.getString("id"));
                location.setCountry(rs.getString("country"));
                location.setCountry(rs.getString("city"));
            }
        } catch(SQLException e){
            throw new RuntimeException("An error occurred while trying to get a location from the database.");
        }
        return location;
    }
}
