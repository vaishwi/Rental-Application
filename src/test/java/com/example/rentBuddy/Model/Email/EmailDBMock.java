package com.example.rentBuddy.Model.Email;

import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;
import com.example.rentbuddy.Model.Email.IEmail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDBMock implements IEmailPersistence {
    @Override
    public IEmail loadReceiverDetail(int advertisementID) {
        String avaibility = "weekends";
        String availableFrom = "09:00:00";
        String availableTo = "15:30:00";

        IEmail email = null;

        if (advertisementID == 2) {
            String emailTo = "vaishwi@gmail.com";
            avaibility = avaibility + availableFrom + availableTo;
            email = SpringIEmailMock.createWithEmailToAvaibility(emailTo, avaibility);

            return email;
        } else {
            return email;
        }
    }

    @Override
    public List<IEmail> loadLandlordEmails(String landlordID) {

        List<IEmail> inboxEmails = new ArrayList<IEmail>();
        if (landlordID.equals("vaishwipatel82110@gmail.com")) {
            String emailFrom = "xyz@gmai.com";
            String message = "HI I am Vaishwi.";
            IEmail email = SpringIEmailMock.createWithEmailFromMessage(emailFrom, message);
            inboxEmails.add(email);

            emailFrom = "yyz@gmail.com";
            message = "Hi I am yyz.";
            IEmail email1 = SpringIEmailMock.createWithEmailFromMessage(emailFrom, message);
            inboxEmails.add(email1);

            return inboxEmails;
        } else if (landlordID.equals("kaya@gmail.com")) {
            return inboxEmails;
        } else {
            return null;
        }
    }

    @Override
    public EmailResult saveEmailInfo(String emailFrom, String emailTo, String message) throws SQLException {
        return EmailResult.SUCCESS;
    }
}
