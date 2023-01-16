package com.example.rentbuddy.Model.Email;

import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;

import java.util.List;

public interface IEmail {

    enum EmailSendResult {
        SUCCESS,
        FAILURE
    }

    IEmailPersistence.EmailResult save(String emailFrom, String emailTo, String message, IEmailPersistence emailPersistence);

    IEmail loadReceiver(int advertisementID, IEmailPersistence emailPersistence);

    List<IEmail> loadLandlordInbox(String landlordId, IEmailPersistence emailPersistence);

    EmailSendResult sendEmail(String emailTo, String message, String subject);
}
