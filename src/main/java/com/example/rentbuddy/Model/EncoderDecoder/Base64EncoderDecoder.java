package com.example.rentbuddy.Model.EncoderDecoder;

import java.util.Base64;

public class Base64EncoderDecoder implements IEncoderDecoder {
    @Override
    public String encode(String stringToEncode) {
        if (stringToEncode == null) {
            return null;
        }
        String encodedString = Base64.getEncoder().encodeToString(stringToEncode.getBytes());
        return encodedString;
    }

    @Override
    public String decode(String stringToDecode) {
        if (stringToDecode == null) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(stringToDecode);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
