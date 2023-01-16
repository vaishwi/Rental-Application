package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.Model.User.IUser;
import com.example.rentbuddy.Model.User.UserFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDB implements IUserPersistence {
    private IDatabase database = null;

    public UserDB(IDatabase database) {
        this.database = database;
    }

    @Override
    public IUser loadUser(String userId) throws SQLException {

        IUser user = null;

        ResultSet userInfo = null;
        String procedureName = " loadUser";
        List<Object> param = new ArrayList<>();
        param.add(userId);

        userInfo = database.executeProcedureWithParams(procedureName, param);

        int rowCount = 0;
        while (userInfo.next()) {
            rowCount++;

            String userType = userInfo.getString("userType");
            String password = userInfo.getString("password");

            user = UserFactory.instance().createUser(password, userType);

        }
        return user;
    }

    public IUserPersistence.StorageResult updatePassword(String userId, String password) throws SQLException {

        String procedureName = " updatePassward";

        List<Object> param = new ArrayList<Object>();
        ;
        param.add(userId);
        param.add(password);

        database.executeProcedureWithParams(procedureName, param);

        return StorageResult.SUCCESS;
    }

    @Override
    public StorageResult saveUser(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType) throws SQLException {

        String procedureName = " saveUser";

        List<Object> param = null;
        try {
            param = convertUserToListOfParams(userId, password, firstName, lastName, dateofBirth, contactNumber, userType);
        } catch (ParseException e) {
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }

        database.executeProcedureWithParams(procedureName, param);

        return StorageResult.SUCCESS;
    }

    private List<Object> convertUserToListOfParams(String userId, String password, String firstName, String lastName, String dateofBirth, String contactNumber, String userType) throws ParseException {
        List<Object> params = new ArrayList<Object>();

        params.add(userId);
        params.add(password);
        params.add(firstName);
        params.add(lastName);
        Date sqlFormatdate = stringToSqlDateConvert(dateofBirth);
        params.add(sqlFormatdate);
        params.add(contactNumber);
        params.add(userType);
        return params;
    }

    private Date stringToSqlDateConvert(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date utilDate = sdf.parse(date);
        java.sql.Date sqlDate = new Date(utilDate.getTime());

        return sqlDate;
    }

}
