package com.example.rentbuddy.PersistenceLayer;

import com.example.rentbuddy.Model.Email.IEmail;

import java.sql.SQLException;
import java.util.List;

public interface IEmailPersistence {

    enum EmailResult {
        STORAGE_FAILURE,
        SUCCESS
    }

    EmailResult saveEmailInfo(String emailFrom, String emailTo, String message) throws SQLException;

    IEmail loadReceiverDetail(int advertisementID) throws SQLException;

    List<IEmail> loadLandlordEmails(String landlordID);

}
