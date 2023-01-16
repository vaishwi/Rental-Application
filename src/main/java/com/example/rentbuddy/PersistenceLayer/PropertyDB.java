package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.Model.Advertisement.FilterAdvertisement;
import com.example.rentbuddy.Model.Property.PropertyResult;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class PropertyDB implements IPropertyPersistence {

    private IDatabase database = null;

    public PropertyDB(IDatabase database) {
        this.database = database;
    }

    @Override
    public PropertyResults getProperties(String location, List<FilterAdvertisement> propertyList) {

        try {
            ResultSet propertyInfo = null;
            String procedureName = " loadProperty";
            List<Object> param = new ArrayList<>();

            param.add(location);
            propertyInfo = database.executeProcedureWithParams(procedureName, param);

            int rowCount = 0;
            while (propertyInfo.next()) {
                FilterAdvertisement property = new FilterAdvertisement();
                rowCount++;
                property.setProperty_id(Integer.parseInt(propertyInfo.getString("property_id")));
                property.setPrice(propertyInfo.getString("price"));
                property.setBathRooms(Integer.parseInt(propertyInfo.getString("bathrooms")));
                property.setRooms(propertyInfo.getString("rooms"));
                propertyList.add(property);
            }

            if (rowCount == 0) {
                return PropertyResults.NO_PROPERTIES;
            } else {
                return PropertyResults.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return PropertyResults.FAILURE;
        }
    }

    @Override
    public PropertyResults filterProperties(FilterAdvertisement tenant, List<FilterAdvertisement> propertyList) {
        try {
            ResultSet propertyInfo = null;
            String procedureName = " filterProperty";
            List<Object> param = new ArrayList<>();

            if (tenant.getProperty_type().equals("Type")) {
                param.add(null);
            } else {
                param.add(tenant.getProperty_type());
            }
            if (String.valueOf(tenant.getRooms()).equals("Rooms")) {
                param.add(null);
            } else {
                param.add(tenant.getRooms());
            }
            if (String.valueOf(tenant.getPrice()).equals("Price")) {
                param.add(null);
                param.add(null);
            } else {
                String[] price = tenant.getPrice().split("-");
                param.add(Integer.parseInt(price[0]));
                param.add(Integer.parseInt(price[1]));
            }
            if (String.valueOf(tenant.getLocation()).equals("location")) {
                param.add(null);
            } else {
                param.add(tenant.getLocation());
            }


            propertyInfo = database.executeProcedureWithParams(procedureName, param);

            int rowCount = 0;
            while (propertyInfo.next()) {
                FilterAdvertisement property = new FilterAdvertisement();
                rowCount++;
                property.setProperty_id(Integer.parseInt(propertyInfo.getString("property_id")));
                property.setPrice(propertyInfo.getString("price"));
                property.setBathRooms(Integer.parseInt(propertyInfo.getString("bathrooms")));
                property.setRooms(propertyInfo.getString("rooms"));
                propertyList.add(property);
            }
            if (rowCount == 0) {
                return PropertyResults.NO_PROPERTIES;
            } else {
                return PropertyResults.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return PropertyResults.FAILURE;
        }
    }

    private String convertBlobToString(Blob inputFile) throws SQLException {

        byte[] byteData = inputFile.getBytes(1, Math.toIntExact((inputFile.length())));
        String imageString = new String(byteData);
        return imageString;
    }

    @Override
    public PropertyResults ViewProperties(int propertyId, PropertyResult property) {
        try {
            ResultSet propertyInfo = null;
            String procedureName = " viewProperty";
            List<Object> param = new ArrayList<>();
            param.add(propertyId);
            propertyInfo = database.executeProcedureWithParams(procedureName, param);
            int rowCount = 0;

            while (propertyInfo.next()) {

                rowCount++;
                property.setProperty_id(Integer.parseInt(propertyInfo.getString("property_id")));
                property.setDescription(propertyInfo.getString("description"));
                property.setProperty_type(propertyInfo.getString("property_type"));
                property.setPrice(propertyInfo.getInt("price"));
                property.setRooms(propertyInfo.getInt("rooms"));
                property.setBathrooms(propertyInfo.getInt("bathrooms"));
                property.setBuilding_number(propertyInfo.getInt("building_number"));
                property.setUser_id(propertyInfo.getString("user_id"));
                property.setLocation_name(propertyInfo.getString("location_name"));
                property.setPhotos(convertBlobToString(propertyInfo.getBlob("photos")));

            }
            if (rowCount == 0) {
                return PropertyResults.NO_PROPERTIES;
            } else {
                return PropertyResults.SUCCESS;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return PropertyResults.FAILURE;
        }

    }
}