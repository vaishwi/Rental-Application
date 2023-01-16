package com.example.rentbuddy.Model.EncoderDecoder;

public class EncoderDecoderFactory implements IEncoderDecoderFactory {

    private static EncoderDecoderFactory uniqueInstance = null;

    private EncoderDecoderFactory() {

    }

    public static EncoderDecoderFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new EncoderDecoderFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IEncoderDecoder createEncoderDecoder() {
        return new Base64EncoderDecoder();
    }
}
