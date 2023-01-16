package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.DatabaseFactory;
import com.example.rentbuddy.Database.IDatabase;

public class UserPersistenceFactory implements IUserPersistenceFactory {
    private static UserPersistenceFactory uniqueInstance = null;

    private UserPersistenceFactory() {
    }

    public static UserPersistenceFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserPersistenceFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IUserPersistence createUserPersistence() {
        IDatabase database = DatabaseFactory.instance().createDatabase();
        return new UserDB(database);
    }
}
