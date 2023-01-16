package com.example.rentbuddy.Model.User;


import com.example.rentbuddy.Model.EncoderDecoder.EncoderDecoderFactory;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;

public class UserFactory implements IUserFactory {
    private static UserFactory uniqueInstance = null;

    private UserFactory() {

    }

    public static UserFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IUser createUser(String password, String userType) {
        return new User(password, userType);
    }

    @Override
    public IUser createUser() {
        return new User();
    }

    @Override
    public IUser createDecryptedUser(String encryptedUserId) {
        IEncoderDecoder encoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
        String decryptedUserId = encoderDecoder.decode(encryptedUserId);
        return new User(decryptedUserId);
    }
}
