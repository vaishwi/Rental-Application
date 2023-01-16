package com.example.rentBuddy.Model.EncoderDecoder;

import com.example.rentbuddy.Model.EncoderDecoder.EncoderDecoderFactory;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64EncoderDecoderTest {

    @Test
    public void encodeTest() {
        IEncoderDecoder base64EncoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
        String stringToEncode = "vaishwipatel82110@gmail.com";
        String encodedString = base64EncoderDecoder.encode(stringToEncode);

        Assertions.assertEquals("dmFpc2h3aXBhdGVsODIxMTBAZ21haWwuY29t", encodedString);

    }

    @Test
    public void nullEncodeTest() {
        IEncoderDecoder base64EncoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
        String stringToEncode = null;
        String encodedString = base64EncoderDecoder.encode(stringToEncode);

        Assertions.assertEquals(null, encodedString);
    }

    @Test
    public void decodeTest() {
        IEncoderDecoder base64EncoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
        String stringToDecode = "dmFpc2h3aXBhdGVsODIxMTBAZ21haWwuY29t";
        String decodedString = base64EncoderDecoder.decode(stringToDecode);

        Assertions.assertEquals("vaishwipatel82110@gmail.com", decodedString);
    }

    @Test
    public void nulldecodeTest() {
        IEncoderDecoder base64EncoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
        String stringToDecode = null;
        String decodedString = base64EncoderDecoder.decode(stringToDecode);

        Assertions.assertEquals(null, decodedString);
    }

}
