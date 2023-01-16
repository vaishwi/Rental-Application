package com.example.rentbuddy.Model.Advertisement;

public class AdvertisementFactory implements IAdvertisementFactory {
    private static AdvertisementFactory uniqueInstance = null;

    private AdvertisementFactory() {

    }

    public static AdvertisementFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new AdvertisementFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IAdvertisement createAdvertisement() {
        return new Advertisement();
    }

}
