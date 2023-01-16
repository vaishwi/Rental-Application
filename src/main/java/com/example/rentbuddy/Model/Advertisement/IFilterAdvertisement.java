package com.example.rentbuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;

import java.util.List;

public interface IFilterAdvertisement {

    IPropertyPersistence.PropertyResults getProperties(String location_name, List<FilterAdvertisement> propertyList, IPropertyPersistence propertyPersistence);

    IPropertyPersistence.PropertyResults filterProperties(FilterAdvertisement tenant, List<FilterAdvertisement> propertyList, IPropertyPersistence propertyPersistence);

}
