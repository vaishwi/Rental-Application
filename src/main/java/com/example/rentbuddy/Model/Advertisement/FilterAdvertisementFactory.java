package com.example.rentbuddy.Model.Advertisement;

public class FilterAdvertisementFactory implements IFilterAdvertisementFactory {

    private static FilterAdvertisementFactory uniqueInstance = null;

    private FilterAdvertisementFactory() {

    }

    public static FilterAdvertisementFactory instance() {

        if (null == uniqueInstance) {
            uniqueInstance = new FilterAdvertisementFactory();
        }
        return uniqueInstance;

    }


    @Override
    public IFilterAdvertisement create() {
        return new FilterAdvertisement();
    }
}
