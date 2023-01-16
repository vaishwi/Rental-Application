package com.example.rentbuddy.Database;

public class DatabaseFactory implements IDatabaseFactory {

    private static DatabaseFactory uniqueInstance = null;

    private DatabaseFactory() {

    }

    public static DatabaseFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new DatabaseFactory();
        }
        return uniqueInstance;
    }


    @Override
    public IDatabase createDatabase() {
        return MySQLDatabase.instance();
    }
}
