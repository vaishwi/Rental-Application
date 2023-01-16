package com.example.rentBuddy.Model.FilterAdvertisement;

import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;
import com.example.rentbuddy.Model.Advertisement.FilterAdvertisement;
import com.example.rentbuddy.Model.Advertisement.IFilterAdvertisement;
import com.example.rentbuddy.Model.Property.IPropertyResult;
import com.example.rentbuddy.Model.Property.PropertyResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterAdvertisementTest {

    @Test
    void getPropertyTest() {
        IFilterAdvertisement propertyList = new FilterAdvertisement();
        IPropertyPersistence dbMock = new PropertyDBMock();
        List<FilterAdvertisement> list = new ArrayList<>();

        IPropertyPersistence.PropertyResults result = propertyList.getProperties("springgarden", list, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.SUCCESS, result);

    }

    @Test
    void getnopropertyTest() {
        IFilterAdvertisement propertyList = new FilterAdvertisement();
        IPropertyPersistence dbMock = new PropertyDBMock();
        List<FilterAdvertisement> list = new ArrayList<>();

        IPropertyPersistence.PropertyResults result = propertyList.getProperties("lacewood", list, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.NO_PROPERTIES, result);
    }

    @Test
    void getfiltersTest() {

        IFilterAdvertisement propertyList = new FilterAdvertisement();
        IPropertyPersistence dbMock = new PropertyDBMock();
        List<FilterAdvertisement> list = new ArrayList<>();

        FilterAdvertisement filterAdvertisement = new FilterAdvertisement();
        filterAdvertisement.setRooms("1BHK");
        filterAdvertisement.setLocation("springgarden");
        filterAdvertisement.setPrice("0-500");
        filterAdvertisement.setProperty_type("Apartment");

        IPropertyPersistence.PropertyResults result = propertyList.filterProperties(filterAdvertisement, list, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.SUCCESS, result);

    }

    @Test
    void getfiltersNoPropertyTest() {

        IFilterAdvertisement propertyList = new FilterAdvertisement();
        IPropertyPersistence dbMock = new PropertyDBMock();
        List<FilterAdvertisement> list = new ArrayList<>();

        FilterAdvertisement filterAdvertisement = new FilterAdvertisement();
        filterAdvertisement.setLocation("lacewood");


        IPropertyPersistence.PropertyResults result = propertyList.filterProperties(filterAdvertisement, list, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.NO_PROPERTIES, result);
    }

    @Test
    void getfiltersFailureTest() {

        IFilterAdvertisement propertyList = new FilterAdvertisement();
        IPropertyPersistence dbMock = new PropertyDBMock();
        List<FilterAdvertisement> list = new ArrayList<>();

        FilterAdvertisement filterAdvertisement = new FilterAdvertisement();
        filterAdvertisement.setRooms("1BHK");
        filterAdvertisement.setLocation("mumford");
        filterAdvertisement.setPrice("0-500");


        IPropertyPersistence.PropertyResults result = propertyList.filterProperties(filterAdvertisement, list, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.FAILURE, result);

    }

    @Test
    void viewPropertyTest() {
        IPropertyResult propertyList = new PropertyResult();
        IPropertyPersistence dbMock = new PropertyDBMock();
        PropertyResult propertyResult = new PropertyResult();
        IPropertyPersistence.PropertyResults result = propertyList.getViewProperties(1, propertyResult, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.SUCCESS, result);
    }

    @Test
    void viewnopropertyTest() {
        IPropertyResult propertyList = new PropertyResult();
        IPropertyPersistence dbMock = new PropertyDBMock();
        PropertyResult propertyResult = new PropertyResult();

        IPropertyPersistence.PropertyResults result = propertyList.getViewProperties(30, propertyResult, dbMock);
        Assertions.assertEquals(IPropertyPersistence.PropertyResults.NO_PROPERTIES, result);
    }

}
