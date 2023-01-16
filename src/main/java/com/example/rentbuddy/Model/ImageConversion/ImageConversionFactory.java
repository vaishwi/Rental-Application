package com.example.rentbuddy.Model.ImageConversion;

public class ImageConversionFactory implements IImageConversionFactory {
    private static ImageConversionFactory uniqueInstance = null;

    private ImageConversionFactory() {

    }

    public static ImageConversionFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new ImageConversionFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IImageConversion createImageConverter() {
        return new ImageConversion();
    }
}
