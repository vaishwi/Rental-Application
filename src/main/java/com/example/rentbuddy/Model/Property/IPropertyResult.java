package com.example.rentbuddy.Model.Property;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;

public interface IPropertyResult {
    IPropertyPersistence.PropertyResults getViewProperties(int propertyId, PropertyResult propertyResult, IPropertyPersistence propertyPersistence);
}
