package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Model.Advertisement.FilterAdvertisement;
import com.example.rentbuddy.Model.Property.PropertyResult;

import java.util.List;

public interface IPropertyPersistence {

    PropertyResults getProperties(String location_name, List<FilterAdvertisement> propertyList);

    PropertyResults filterProperties(FilterAdvertisement tenantFilter, List<FilterAdvertisement> propertyList);

    PropertyResults ViewProperties(int propertyId, PropertyResult propertyResult);

    enum PropertyResults {
        SUCCESS,
        FAILURE,
        NO_PROPERTIES,
    }
}
