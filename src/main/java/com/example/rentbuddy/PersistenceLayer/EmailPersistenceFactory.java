package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.DatabaseFactory;
import com.example.rentbuddy.Database.IDatabase;

public class EmailPersistenceFactory implements IEmailPersistenceFactory {
    private static EmailPersistenceFactory uniqueInstance = null;

    private EmailPersistenceFactory() {

    }

    public static EmailPersistenceFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new EmailPersistenceFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IEmailPersistence createEmailPersistence() {
        IDatabase database = DatabaseFactory.instance().createDatabase();
        return new EmailDB(database);
    }
}
