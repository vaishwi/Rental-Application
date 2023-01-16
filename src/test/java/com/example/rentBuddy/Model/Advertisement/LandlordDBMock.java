package com.example.rentBuddy.Model.Advertisement;

import com.example.rentbuddy.PersistenceLayer.ILandlordPersistence;
import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;
import com.example.rentbuddy.Model.ImageConversion.ImageConversionFactory;

import java.util.List;

public class LandlordDBMock implements ILandlordPersistence {
    private Advertisement createAdvertisement(String landlordId) {
        Advertisement advertisement1 = new Advertisement();
        advertisement1.setAdvertisementID(2);
        advertisement1.setUserID(landlordId);
        advertisement1.setTitle("Title for test");
        advertisement1.setDescription("Description for Test");
        advertisement1.setPrice(1000);
        advertisement1.setNoOfBedrooms(2);
        advertisement1.setLandlordAvailability("weekdays");
        advertisement1.setNoOfBathrooms(1);
        advertisement1.setAvailableFrom("16:00");
        advertisement1.setAvailableTill("21:00");
        advertisement1.setBuildingNumber(1333);
        advertisement1.setArea("Downtown");
        advertisement1.setDisplayPhoto("/test.img");
        return advertisement1;
    }

    private Advertisement getAd(String landlordId) {
        Advertisement advertisement;
        advertisement = createAdvertisement(landlordId);
        return advertisement;
    }

    private Advertisement delete(int advertisementID) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementID(advertisementID);
        return advertisement;
    }

    private Advertisement edit(int price) {
        Advertisement advertisement = new Advertisement();
        advertisement.setPrice(price);
        return advertisement;
    }

    @Override
    public AdvertisementResult showAdvertisements(String landlordId, List<Advertisement> advertisementsList) {
        advertisementsList.add(createAdvertisement(landlordId));
        if (landlordId.equals("vaishvi@gmail.com")) {
            return AdvertisementResult.NO_ADVERTISEMENTS;
        } else if (landlordId.isBlank()) {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }

    @Override
    public AdvertisementResult getAdvertisement(int advertisementId, Advertisement advertisement, IImageConversion imageConverter) {
        if (advertisementId == 2) {
            createAdvertisement("rakeeb@gmail.com");
        } else if (advertisementId == 4) {
            return AdvertisementResult.NO_ADVERTISEMENTS;
        } else {
            return AdvertisementResult.FAILED;
        }
        return AdvertisementResult.SUCCESS;
    }

    @Override
    public AdvertisementResult addAdvertisement(Advertisement advertisement) {
        createAdvertisement("rakeeb@gmail.com");
        if (advertisement.getUserID().equals("rakeeb@gmail.com")) {
            return AdvertisementResult.SUCCESS;
        } else {
            return AdvertisementResult.FAILED;
        }
    }

    @Override
    public AdvertisementResult editAdvertisement(Advertisement advertisement) {
        advertisement = edit(advertisement.getPrice());
        if (advertisement.getPrice() == 750) {
            return AdvertisementResult.SUCCESS;
        } else {
            return AdvertisementResult.FAILED;
        }
    }

    @Override
    public AdvertisementResult deleteAdvertisement(int advertisementID) {
        Advertisement advertisement = new Advertisement();
        IImageConversion imageConversion = ImageConversionFactory.instance().createImageConverter();
        ILandlordPersistence.AdvertisementResult result = getAdvertisement(advertisementID, advertisement, imageConversion);
        if (result.equals(AdvertisementResult.SUCCESS)) {
            delete(advertisementID);
            return AdvertisementResult.SUCCESS;
        } else {
            return AdvertisementResult.FAILED;
        }
    }

    @Override
    public AdvertisementResult deleteAllAdvertisements(String landlordId) {
        Advertisement advertisement = new Advertisement();
        advertisement = getAd(landlordId);
        if (advertisement.getUserID().equals("rakeeb@gmail.com")) {
            delete(advertisement.getAdvertisementID());
            return AdvertisementResult.SUCCESS;
        } else {
            return AdvertisementResult.FAILED;
        }
    }
}
