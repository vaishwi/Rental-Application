package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LandlordDB implements ILandlordPersistence {

    private IDatabase database = null;

    public LandlordDB(IDatabase database) {

        this.database = database;
    }

    @Override
    public AdvertisementResult getAdvertisement(int advertisementId, Advertisement advertisement, IImageConversion imageConverter) {

        String procedureName = " getAdvertisement";
        ResultSet advertisementsResult;
        List<Object> params = new ArrayList<>();
        params.add(advertisementId);
        try {
            advertisementsResult = database.executeProcedureWithParams(procedureName, params);
            setAttributes(advertisementsResult, advertisementId, advertisement, imageConverter);
            if (advertisement.getAdvertisementID() != advertisementId) {
                return AdvertisementResult.NO_ADVERTISEMENTS;
            } else {
                return AdvertisementResult.SUCCESS;
            }
        } catch (SQLException e) {
            return AdvertisementResult.FAILED;
        }
    }

    private void setAttributes(ResultSet advertisementsResult, int advertisementId, Advertisement advertisement, IImageConversion imageConverter) throws SQLException {

        while (advertisementsResult.next()) {
            advertisement.setAdvertisementID(advertisementId);
            advertisement.setPropertyType(advertisementsResult.getString("property_type"));
            advertisement.setTitle(advertisementsResult.getString("title"));
            advertisement.setDescription(advertisementsResult.getString("description"));
            advertisement.setPrice(advertisementsResult.getInt("price"));
            advertisement.setNoOfBedrooms(advertisementsResult.getInt("rooms"));
            advertisement.setLandlordAvailability(advertisementsResult.getString("availability"));
            advertisement.setNoOfBathrooms(advertisementsResult.getInt("bathrooms"));
            advertisement.setAvailableFrom(advertisementsResult.getString("available_from"));
            advertisement.setAvailableTill(advertisementsResult.getString("available_to"));
            advertisement.setBuildingNumber(advertisementsResult.getInt("building_number"));
            advertisement.setArea(advertisementsResult.getString("location_name"));
            advertisement.setDisplayPhoto(imageConverter.convert(advertisementsResult.getBlob("photos")));
        }
    }

    @Override
    public AdvertisementResult showAdvertisements(String landlordID, List<Advertisement> advertisementList) {

        ResultSet advertisementsResult = null;
        String procedureName = " showAdvertisements";
        List<Object> params = new ArrayList<>();
        try {
            params.add(landlordID);
            advertisementsResult = database.executeProcedureWithParams(procedureName, params);
            int rowCount = 0;
            while (advertisementsResult.next()) {
                rowCount++;
                Advertisement advertisement = new Advertisement();
                advertisement.setAdvertisementID(advertisementsResult.getInt("property_id"));
                advertisement.setUserID(landlordID);
                advertisement.setPropertyType(advertisementsResult.getString("property_type"));
                advertisement.setTitle(advertisementsResult.getString("title"));
                advertisement.setDescription(advertisementsResult.getString("description"));
                advertisement.setPrice(advertisementsResult.getInt("price"));
                advertisement.setNoOfBedrooms(advertisementsResult.getInt("rooms"));
                advertisement.setLandlordAvailability(advertisementsResult.getString("availability"));
                advertisement.setNoOfBathrooms(advertisementsResult.getInt("bathrooms"));
                advertisement.setAvailableFrom(advertisementsResult.getString("available_from"));
                advertisement.setAvailableTill(advertisementsResult.getString("available_to"));
                advertisement.setBuildingNumber(advertisementsResult.getInt("building_number"));
                advertisement.setArea(advertisementsResult.getString("location_name"));
                advertisementList.add(advertisement);
            }
            if (rowCount == 0) {
                return AdvertisementResult.NO_ADVERTISEMENTS;
            } else {
                return AdvertisementResult.SUCCESS;
            }
        } catch (SQLException e) {
            return AdvertisementResult.FAILED;
        }

    }

    @Override
    public AdvertisementResult addAdvertisement(Advertisement advertisement) {
        try {
            String procedureName = " addAdvertisement";
            List<Object> params = new ArrayList<>();
            String photo = Base64.getEncoder().encodeToString(advertisement.getPhotos().getBytes());
            params.add(advertisement.getUserID());
            params.add(advertisement.getPropertyType());
            params.add(advertisement.getTitle());
            params.add(advertisement.getDescription());
            params.add(advertisement.getPrice());
            params.add(advertisement.getNoOfBedrooms());
            params.add(advertisement.getNoOfBathrooms());
            params.add(advertisement.getLandlordAvailability());
            params.add(advertisement.getAvailableFrom());
            params.add(advertisement.getAvailableTill());
            params.add(advertisement.getBuildingNumber());
            params.add(advertisement.getStreetName());
            params.add(advertisement.getArea());
            params.add(advertisement.getCityName());
            params.add(advertisement.getCountryName());
            params.add(photo);
            database.executeProcedureWithParams(procedureName, params);

        } catch (SQLException | IOException e) {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }

    @Override
    public AdvertisementResult editAdvertisement(Advertisement advertisement) {
        try {
            String procedureName = " editAdvertisement";
            List<Object> params = new ArrayList<>();
            params.add(advertisement.getUserID());
            params.add(advertisement.getAdvertisementID());
            params.add(advertisement.getPropertyType());
            params.add(advertisement.getTitle());
            params.add(advertisement.getDescription());
            params.add(advertisement.getPrice());
            params.add(advertisement.getNoOfBedrooms());
            params.add(advertisement.getLandlordAvailability());
            params.add(advertisement.getNoOfBathrooms());
            params.add(advertisement.getAvailableFrom());
            params.add(advertisement.getAvailableTill());
            params.add(advertisement.getBuildingNumber());
            params.add(advertisement.getArea());
            database.executeProcedureWithParams(procedureName, params);
        } catch (SQLException e) {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }

    @Override
    public AdvertisementResult deleteAdvertisement(int advertisementID) {
        try {
            String procedureName = " deleteAdvertisement";
            List<Object> params = new ArrayList<>();
            params.add(advertisementID);
            database.executeProcedureWithParams(procedureName, params);
        } catch (SQLException e) {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }

    @Override
    public AdvertisementResult deleteAllAdvertisements(String landlordId) {
        try {
            String procedureName = " deleteAllAdvertisements";
            List<Object> params = new ArrayList<>();
            params.add(landlordId);
            database.executeProcedureWithParams(procedureName, params);
        } catch (SQLException e) {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }
}
