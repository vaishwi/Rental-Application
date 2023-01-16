package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Model.User.IUser;

import java.sql.SQLException;

public interface IUserPersistence {
    enum StorageResult {
        STORAGE_FAILURE,
        SUCCESS
    }

    IUser loadUser(String userName) throws SQLException;

    StorageResult saveUser(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType) throws SQLException;

    StorageResult updatePassword(String userId, String password) throws SQLException;

}
