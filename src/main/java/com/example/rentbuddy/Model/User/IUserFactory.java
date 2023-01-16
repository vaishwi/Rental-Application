package com.example.rentbuddy.Model.User;

public interface IUserFactory {
    IUser createUser();

    IUser createUser(String password, String userType);

    IUser createDecryptedUser(String encryptedUserId);

}
