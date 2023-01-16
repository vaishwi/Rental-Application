package com.example.rentbuddy.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    void connect() throws SQLException;
    ResultSet executeProcedureWithParams(String procedureName, List<Object> parameters) throws SQLException;

}
