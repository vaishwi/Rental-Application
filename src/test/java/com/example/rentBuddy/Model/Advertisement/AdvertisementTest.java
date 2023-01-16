package com.example.rentBuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.ILandlordPersistence;
import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.Advertisement.IAdvertisement;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;
import com.example.rentbuddy.Model.ImageConversion.ImageConversionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementTest {
    @Test
    void getAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        IImageConversion imageConversion = ImageConversionFactory.instance().createImageConverter();
        ILandlordPersistence.AdvertisementResult result = advertisement.getDetails(2, dbMock, imageConversion);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void getEmptyAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        IImageConversion imageConversion = ImageConversionFactory.instance().createImageConverter();
        ILandlordPersistence.AdvertisementResult result = advertisement.getDetails(4, dbMock, imageConversion);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.NO_ADVERTISEMENTS, result);
    }

    @Test
    void getStorageFailureTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        IImageConversion imageConversion = ImageConversionFactory.instance().createImageConverter();
        ILandlordPersistence.AdvertisementResult result = advertisement.getDetails(0, dbMock, imageConversion);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }

    @Test
    void getAdvertisementsTest() {
        IAdvertisement advertisement = new Advertisement();
        List<Advertisement> advertisementsList = new ArrayList<>();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.getAdvertisements("rakeeb@gmail.com", advertisementsList, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void getStorageFailureAdvertisementsTest() {
        IAdvertisement advertisement = new Advertisement();
        List<Advertisement> advertisementsList = new ArrayList<>();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.getAdvertisements("", advertisementsList, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }

    @Test
    void getEmptyAdvertisementsTest() {
        IAdvertisement advertisement = new Advertisement();
        List<Advertisement> advertisementsList = new ArrayList<>();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.getAdvertisements("vaishvi@gmail.com", advertisementsList, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.NO_ADVERTISEMENTS, result);
    }

    @Test
    void addAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ((Advertisement) advertisement).setUserID("rakeeb@gmail.com");
        ILandlordPersistence.AdvertisementResult result = advertisement.addAdvertisement((Advertisement) advertisement, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void addStorageFailureAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ((Advertisement) advertisement).setUserID("test@gmail.com");
        ILandlordPersistence.AdvertisementResult result = advertisement.addAdvertisement((Advertisement) advertisement, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }

    @Test
    void editAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ((Advertisement) advertisement).setPrice(750);
        ILandlordPersistence.AdvertisementResult result = advertisement.editAdvertisement((Advertisement) advertisement, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void editFailureAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ((Advertisement) advertisement).setPrice(10000);
        ILandlordPersistence.AdvertisementResult result = advertisement.editAdvertisement((Advertisement) advertisement, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }

    @Test
    void deleteAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.deleteAdvertisement(2, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void deleteEmptyAdvertisementTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.deleteAdvertisement(10, dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }

    @Test
    void deleteAllAdvertisementsTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.deleteAllAdvertisements("rakeeb@gmail.com", dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.SUCCESS, result);
    }

    @Test
    void deleteFailureAllAdvertisementsTest() {
        IAdvertisement advertisement = new Advertisement();
        ILandlordPersistence dbMock = new LandlordDBMock();
        ILandlordPersistence.AdvertisementResult result = advertisement.deleteAllAdvertisements("test@gmail.com", dbMock);
        Assertions.assertEquals(ILandlordPersistence.AdvertisementResult.FAILED, result);
    }
}
