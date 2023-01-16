package com.example.rentbuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.ILandlordPersistence;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;

import java.util.List;

public interface IAdvertisement {

    ILandlordPersistence.AdvertisementResult getDetails(int advertisementID, ILandlordPersistence landlordPersistence, IImageConversion imageConverter);

    ILandlordPersistence.AdvertisementResult getAdvertisements(String landlordID, List<Advertisement> advertisementList, ILandlordPersistence landlordPersistence);

    ILandlordPersistence.AdvertisementResult addAdvertisement(Advertisement advertisement, ILandlordPersistence landlordPersistence);

    ILandlordPersistence.AdvertisementResult editAdvertisement(Advertisement advertisement, ILandlordPersistence landlordPersistence);

    ILandlordPersistence.AdvertisementResult deleteAdvertisement(int advertisementID, ILandlordPersistence landlordPersistence);

    ILandlordPersistence.AdvertisementResult deleteAllAdvertisements(String landlordID, ILandlordPersistence landlordPersistence);
}
