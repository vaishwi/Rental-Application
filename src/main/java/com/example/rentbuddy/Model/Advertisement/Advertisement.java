package com.example.rentbuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.ILandlordPersistence;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Advertisement implements IAdvertisement {
    private int advertisementID;
    private String userID;
    private String title;
    private String description;
    private String propertyType;
    private int price;
    private int noOfBedrooms;
    private int noOfBathrooms;
    private String landlordAvailability;
    private String availableFrom;
    private String availableTill;
    private int buildingNumber;
    private String area;
    private String streetName;
    private String cityName;
    private String countryName;
    private MultipartFile photos;
    private String displayPhoto;

    public Advertisement() {
    }

    @Override
    public ILandlordPersistence.AdvertisementResult getDetails(int advertisementID, ILandlordPersistence landlordPersistence, IImageConversion imageConverter) {
        return landlordPersistence.getAdvertisement(advertisementID, this, imageConverter);
    }

    @Override
    public ILandlordPersistence.AdvertisementResult getAdvertisements(String landlordID, List<Advertisement> advertisementList, ILandlordPersistence landlordPersistence) {
        return landlordPersistence.showAdvertisements(landlordID, advertisementList);
    }

    @Override
    public ILandlordPersistence.AdvertisementResult addAdvertisement(Advertisement advertisement, ILandlordPersistence landlordPersistence) {
        return landlordPersistence.addAdvertisement(advertisement);
    }

    @Override
    public ILandlordPersistence.AdvertisementResult editAdvertisement(Advertisement advertisement, ILandlordPersistence landlordPersistence) {
        return landlordPersistence.editAdvertisement(advertisement);
    }

    @Override
    public ILandlordPersistence.AdvertisementResult deleteAdvertisement(int advertisementID, ILandlordPersistence landlordPersistence) {
        return landlordPersistence.deleteAdvertisement(advertisementID);
    }

    @Override
    public ILandlordPersistence.AdvertisementResult deleteAllAdvertisements(String landlordID, ILandlordPersistence landlordPersistence) {
        return landlordPersistence.deleteAllAdvertisements(landlordID);
    }

    public int getAdvertisementID() {
        return advertisementID;
    }

    public void setAdvertisementID(int advertisementID) {
        this.advertisementID = advertisementID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public String getLandlordAvailability() {
        return landlordAvailability;
    }

    public void setLandlordAvailability(String landlordAvailability) {
        this.landlordAvailability = landlordAvailability;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTill() {
        return availableTill;
    }

    public void setAvailableTill(String availableTill) {
        this.availableTill = availableTill;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public MultipartFile getPhotos() {
        return photos;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public String getDisplayPhoto() {
        return displayPhoto;
    }

    public void setDisplayPhoto(String displayPhoto) {
        this.displayPhoto = displayPhoto;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPhotos(MultipartFile photos) {
        this.photos = photos;
    }
}