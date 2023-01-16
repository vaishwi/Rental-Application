package com.example.rentbuddy.Model.User;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;

import java.sql.SQLException;

public interface IUser {

    enum UserLoginResult {
        VALID_USER,
        INVALID_USER,
        USER_DOES_NOT_EXIST
    }
    enum UserRegisterResult {
        SUCCESS,
        STORAGE_FAILURE,
        USER_EXIST
    }

    IUser load(String userId, IUserPersistence userPersistence);

    IUserPersistence.StorageResult save(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType, IUserPersistence p) throws SQLException;

    IUserPersistence.StorageResult updatePassword(IUserPersistence userPersistence);

    IUser.UserLoginResult loginUser(String userId, String password, IUserPersistence userPersistence);

    IUser.UserRegisterResult register(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType, IUserPersistence userPersistence);

    String generateResetURL(String pageURL, String userId, IEncoderDecoder encoderDecoder);

}
