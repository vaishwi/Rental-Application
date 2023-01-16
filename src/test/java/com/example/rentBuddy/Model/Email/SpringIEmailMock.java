package com.example.rentBuddy.Model.Email;

import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;
import com.example.rentbuddy.Model.Email.IEmail;

import java.sql.SQLException;
import java.util.List;

public class SpringIEmailMock implements IEmail {
    String emailTo;
    String avibility;
    String emailFrom;
    String message;

    public String getEmailTo() {
        return emailTo;
    }

    public String getAvibility() {
        return avibility;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getMessage() {
        return message;
    }

    public static IEmail createWithEmailToAvaibility(String emailTo, String avaibility) {
        SpringIEmailMock email = new SpringIEmailMock();
        email.avibility = avaibility;
        email.emailTo = emailTo;
        return email;
    }

    public static IEmail createWithEmailFromMessage(String emailFrom, String message) {
        SpringIEmailMock email = new SpringIEmailMock();
        email.emailFrom = emailFrom;
        email.message = message;
        return email;
    }

    @Override
    public IEmailPersistence.EmailResult save(String emailFrom, String emailTo, String message, IEmailPersistence emailPersistence) {
        try {
            return emailPersistence.saveEmailInfo(emailFrom, emailTo, message);
        } catch (SQLException e) {
            return IEmailPersistence.EmailResult.STORAGE_FAILURE;
        }
    }

    @Override
    public IEmail loadReceiver(int advertisementID, IEmailPersistence emailPersistence) {

        try {
            return emailPersistence.loadReceiverDetail(advertisementID);

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<IEmail> loadLandlordInbox(String landlordId, IEmailPersistence emailPersistence) {
        return emailPersistence.loadLandlordEmails(landlordId);
    }

    @Override
    public EmailSendResult sendEmail(String emailTo, String message, String subject) {
        return EmailSendResult.SUCCESS;
    }
}
