package com.example.rentBuddy.Model.User;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.Model.User.IUser;
import com.example.rentbuddy.Model.User.UserFactory;

import java.sql.SQLException;

public class UserDBMock implements IUserPersistence {

    @Override
    public IUser loadUser(String userId) {

        if (userId.equals("vaishwi@gmail.com")) {
            IUser user = UserFactory.instance().createUser("314242", "Landlord");

            return user;
        } else if (userId.equals("hetvi@gmail.com")) {
            return null;
        } else {
            return null;
        }
    }

    @Override
    public IUserPersistence.StorageResult saveUser(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType) throws SQLException {

        return StorageResult.SUCCESS;
    }

    @Override
    public IUserPersistence.StorageResult updatePassword(String userID, String password) throws SQLException {
        return StorageResult.SUCCESS;
    }

}
