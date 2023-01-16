package com.example.rentbuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;

import java.util.List;

public class FilterAdvertisement implements IFilterAdvertisement {
    private String keyword;
    private String property_type;
    private String price;
    private String rooms;
    private int bathRooms;
    private String location;
    private int property_id;

    public String getKeyword() {
        return keyword;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }


    public FilterAdvertisement() {
    }

    public FilterAdvertisement(String property_type, String price, String rooms, int bathRooms, String location) {
        this.property_type = property_type;
        this.price = price;
        this.rooms = rooms;
        this.bathRooms = bathRooms;
        this.location = location;
    }

    @Override
    public IPropertyPersistence.PropertyResults getProperties(String location_name, List<FilterAdvertisement> propertyList, IPropertyPersistence propertyPersistence) {
        return propertyPersistence.getProperties(location_name, propertyList);
    }

    @Override
    public IPropertyPersistence.PropertyResults filterProperties(FilterAdvertisement tenant, List<FilterAdvertisement> propertyList, IPropertyPersistence propertyPersistence) {
        return propertyPersistence.filterProperties(tenant, propertyList);
    }

}
