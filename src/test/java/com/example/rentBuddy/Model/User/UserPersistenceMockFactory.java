package com.example.rentBuddy.Model.User;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.PersistenceLayer.IUserPersistenceFactory;

public class UserPersistenceMockFactory implements IUserPersistenceFactory {

    private static UserPersistenceMockFactory uniqueInstance = null;

    private UserPersistenceMockFactory() {

    }

    public static UserPersistenceMockFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserPersistenceMockFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IUserPersistence createUserPersistence() {
        return new UserDBMock();
    }
}
