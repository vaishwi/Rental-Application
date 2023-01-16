package com.example.rentbuddy.Model.ImageConversion;

import java.sql.Blob;

public interface IImageConversion {
    String convert(Blob inputFile);
}
