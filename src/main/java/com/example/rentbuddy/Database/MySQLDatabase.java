package com.example.rentbuddy.Database;

import java.sql.*;
import java.util.List;

public class MySQLDatabase implements IDatabase {
    private Connection connection;
    private static MySQLDatabase instance = null;
    private String databaseUser;
    private String databsePassword;
    private String databasePort;
    private String databaseName;
    private String databaseBaseURL;
    private String databaseUrl;

    private MySQLDatabase() {
        databaseName = System.getenv("DB_DATABASENAME");
        databaseUser = System.getenv("DB_USER");
        databsePassword = System.getenv("DB_PASSWORD");
        databasePort = System.getenv("DB_PORT");
        databaseBaseURL = System.getenv("DB_BASEURL");
    }

    public static MySQLDatabase instance() {
        if (instance == null) {
            instance = new MySQLDatabase();
        }
        return instance;
    }

    @Override
    public void connect() throws SQLException {

        databaseUrl = databaseBaseURL + ":" + databasePort + "/" + databaseName;

        this.connection = DriverManager.getConnection(databaseUrl, this.databaseUser, this.databsePassword);
    }

    @Override
    public ResultSet executeProcedureWithParams(String procedureName, List<Object> parameters) throws SQLException {

        this.connect();

        ResultSet resultSet = null;
        int paramSize = parameters.size();

        String paramsToPassInStoredProcedure = findParameters(paramSize);

        PreparedStatement statement = this.connection.prepareStatement("{ call" + procedureName + "(" + paramsToPassInStoredProcedure + ")}");

        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }
        resultSet = statement.executeQuery();
        return resultSet;
    }

    private String findParameters(int paramSize) {
        String params = "";
        for (int i = 0; i < paramSize; i++) {
            params += "?,";
        }
        return params.substring(0, params.length() - 1);
    }

}