package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.Model.Email.IEmail;
import com.example.rentbuddy.Model.Email.EmailFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDB implements IEmailPersistence {
    private IDatabase database = null;

    public EmailDB(IDatabase database) {
        this.database = database;
    }

    @Override
    public EmailResult saveEmailInfo(String emailFrom, String emailTo, String message) throws SQLException {


        String procedureName = " saveEmailInfo";

        List<Object> param = null;

        param = convertUserToListOfParams(emailFrom, emailTo, message);

        database.executeProcedureWithParams(procedureName, param);

        return EmailResult.SUCCESS;
    }

    @Override
    public IEmail loadReceiverDetail(int advertisementID) throws SQLException {

        IEmail email = null;
        ResultSet emailInfo = null;
        String procedureName = " loadSenderDetail";
        List<Object> param = new ArrayList<>();
        param.add(advertisementID);

        emailInfo = database.executeProcedureWithParams(procedureName, param);

        while (emailInfo.next()) {
            String emailTo = emailInfo.getString("user_id");

            String avaibility = emailInfo.getString("availability") + " " + emailInfo.getString("available_from") + " " + emailInfo.getString("available_to");
            email = EmailFactory.instance().createWithEmailToAvaibility(emailTo, avaibility);
        }

        return email;

    }

    @Override
    public List<IEmail> loadLandlordEmails(String landlordID) {

        ResultSet emailInfo = null;
        List<IEmail> inboxEmails = new ArrayList<IEmail>();
        ;
        String procedureName = " loadEmails";
        List<Object> param = new ArrayList<>();
        param.add(landlordID);

        try {
            emailInfo = database.executeProcedureWithParams(procedureName, param);

            while (emailInfo.next()) {
                String emailFrom = emailInfo.getString("from");
                String message = emailInfo.getString("message");
                IEmail email = EmailFactory.instance().createWithEmailFromMessage(emailFrom, message);
                inboxEmails.add(email);
            }
            return inboxEmails;
        } catch (SQLException e) {
            return null;
        }
    }

    private List<Object> convertUserToListOfParams(String emailFrom, String emailTo, String message) {
        List<Object> params = new ArrayList<Object>();

        params.add(emailFrom);
        params.add(emailTo);
        params.add(message);

        return params;
    }

}
