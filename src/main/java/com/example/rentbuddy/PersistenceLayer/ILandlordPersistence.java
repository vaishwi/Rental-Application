package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;

import java.util.List;

public interface ILandlordPersistence {
    AdvertisementResult showAdvertisements(String landlordId, List<Advertisement> advertisementsList);

    AdvertisementResult getAdvertisement(int advertisementId, Advertisement advertisement, IImageConversion imageConverter);

    AdvertisementResult addAdvertisement(Advertisement advertisement);

    AdvertisementResult editAdvertisement(Advertisement advertisement);

    AdvertisementResult deleteAdvertisement(int advertisementID);

    AdvertisementResult deleteAllAdvertisements(String landlordId);

    public enum AdvertisementResult {
        SUCCESS,
        FAILED,
        NO_ADVERTISEMENTS,
    }
}
