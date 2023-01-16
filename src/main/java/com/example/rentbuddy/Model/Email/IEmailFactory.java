package com.example.rentbuddy.Model.Email;

public interface IEmailFactory {

    IEmail createEmail();

    IEmail createEmail(String emailFrom, String emailTo, String avaibility);

    IEmail createWithEmailToAvaibility(String emailTo, String avaibiltiy);

    IEmail createWithEmailFromMessage(String emailFrom, String message);

}
