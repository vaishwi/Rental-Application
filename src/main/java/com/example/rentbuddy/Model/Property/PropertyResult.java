package com.example.rentbuddy.Model.Property;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;

public class PropertyResult implements IPropertyResult {
    public int property_id;
    public int rooms;
    public int bathrooms;
    public int price;
    public String description;
    public String property_type;
    public String location_name;
    public int building_number;
    public String user_id;

    private String photos;

    public PropertyResult() {
    }

    public PropertyResult(int property_id, int rooms, int bathrooms, int price, String description, String property_type, String location_name, int building_number, String user_id, String photos) {
        this.property_id = property_id;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.price = price;
        this.description = description;
        this.property_type = property_type;
        this.location_name = location_name;
        this.building_number = building_number;
        this.user_id = user_id;
        this.photos = photos;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public IPropertyPersistence.PropertyResults getViewProperties(int propertyId, PropertyResult propertyResult, IPropertyPersistence propertyPersistence) {
        return propertyPersistence.ViewProperties(propertyId, propertyResult);
    }
}
