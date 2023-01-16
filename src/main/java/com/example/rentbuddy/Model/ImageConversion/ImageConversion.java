package com.example.rentbuddy.Model.ImageConversion;

import java.sql.Blob;
import java.sql.SQLException;

public class ImageConversion implements IImageConversion {
    @Override
    public String convert(Blob inputFile) {
        byte[] byteData = new byte[0];
        try {
            byteData = inputFile.getBytes(1, Math.toIntExact((inputFile.length())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String imageString = new String(byteData);

        return imageString;
    }
}
