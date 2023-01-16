package com.example.rentBuddy.Model.FilterAdvertisement;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;
import com.example.rentbuddy.Model.Advertisement.FilterAdvertisement;
import com.example.rentbuddy.Model.Property.PropertyResult;

import java.util.ArrayList;
import java.util.List;

public class PropertyDBMock implements IPropertyPersistence {

    private FilterAdvertisement getSearch(String location_name) {
        FilterAdvertisement Search = new FilterAdvertisement();
        Search.setProperty_type("Apartment");
        Search.setPrice("1234");
        Search.setRooms("2");
        Search.setBathRooms(2);
        Search.setLocation(location_name);

        return Search;
    }

    private PropertyResult viewProperty(int propertyId) {
        PropertyResult propertyresult = new PropertyResult();
        propertyresult.setProperty_id(propertyId);
        propertyresult.setRooms(2);
        propertyresult.setLocation_name("springgarden");
        propertyresult.setDescription("Newly Furnished Apartment");
        propertyresult.setPrice(500);
        propertyresult.setProperty_type("Apartment");
        propertyresult.setBathrooms(2);
        propertyresult.setPhotos("Diagram");
        propertyresult.setUser_id("1");
        return propertyresult;
    }

    @Override
    public PropertyResults getProperties(String location_name, List<FilterAdvertisement> propertyList) {
        FilterAdvertisement property = getSearch(location_name);
        propertyList.add(property);
        if (property.getLocation().equals("springgarden")) {
            return PropertyResults.SUCCESS;
        }
        return PropertyResults.NO_PROPERTIES;
    }

    @Override
    public PropertyResults filterProperties(FilterAdvertisement tenantFilter, List<FilterAdvertisement> propertyList) {

        propertyList = new ArrayList<FilterAdvertisement>();

        if (tenantFilter.getProperty_type() == "Apartment") {
            FilterAdvertisement advertisement = new FilterAdvertisement();
            advertisement.setProperty_id(2);
            propertyList.add(advertisement);
        } else if (tenantFilter.getLocation() == "springgarden") {
            FilterAdvertisement advertisement = new FilterAdvertisement();
            advertisement.setProperty_id(3);
            propertyList.add(advertisement);
        } else if (tenantFilter.getPrice() == "1000-1500") {
            FilterAdvertisement advertisement = new FilterAdvertisement();
            advertisement.setProperty_id(4);
            propertyList.add(advertisement);
        } else if (tenantFilter.getRooms() == "2BHK") {
            FilterAdvertisement advertisement = new FilterAdvertisement();
            advertisement.setProperty_id(5);
            propertyList.add(advertisement);
        } else if (tenantFilter.getLocation() == "lacewood") {
            return PropertyResults.NO_PROPERTIES;
        } else {
            return PropertyResults.FAILURE;
        }
        return PropertyResults.SUCCESS;

    }

    @Override
    public PropertyResults ViewProperties(int propertyId, PropertyResult propertyResult) {
        PropertyResult property = viewProperty(propertyId);


        if (property.getProperty_id() == 1) {
            return PropertyResults.SUCCESS;
        } else if (property.getProperty_id() == 20) {

            return PropertyResults.FAILURE;
        }
        return PropertyResults.NO_PROPERTIES;

    }
}
