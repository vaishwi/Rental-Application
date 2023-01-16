package com.example.rentBuddy.Model.ImageConversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageConversionTest {
    @Test
    void convert() {
        ImageConversionMock imageConverter = new ImageConversionMock();
        String file = "image";
        String result = imageConverter.convert(file);
        Assertions.assertEquals("#5SdfRTR#$image/mNigJC6sEnW/", result);
    }

    @Test
    void nullConvert() {
        ImageConversionMock imageConverter = new ImageConversionMock();
        String file = null;
        String result = imageConverter.convert(file);
        Assertions.assertNull(result);
    }
}
