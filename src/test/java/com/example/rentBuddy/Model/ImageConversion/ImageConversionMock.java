package com.example.rentBuddy.Model.ImageConversion;

public class ImageConversionMock {
    public String convert(String image) {
        String imageConvertedToString;
        if (image == null) {
            imageConvertedToString = null;
        } else {
            imageConvertedToString = "#5SdfRTR#$" + image + "/mNigJC6sEnW/";
        }
        return imageConvertedToString;
    }
}
