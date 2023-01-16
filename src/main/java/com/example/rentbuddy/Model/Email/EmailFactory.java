package com.example.rentbuddy.Model.Email;

public class EmailFactory implements IEmailFactory {

    private static EmailFactory uniqueInstance = null;

    private EmailFactory() {

    }

    public static EmailFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new EmailFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IEmail createEmail() {
        return new SpringEmail();
    }

    @Override
    public IEmail createEmail(String emailFrom, String emailTo, String avaibility) {
        return new SpringEmail(emailFrom, emailTo, avaibility);
    }

    @Override
    public IEmail createWithEmailToAvaibility(String emailTo, String avaibiltiy) {
        SpringEmail email = new SpringEmail();
        email.setEmailTo(emailTo);
        email.setAvaibility(avaibiltiy);
        return email;
    }

    @Override
    public IEmail createWithEmailFromMessage(String emailFrom, String message) {
        SpringEmail email = new SpringEmail();
        email.setEmailFrom(emailFrom);
        email.setMessage(message);
        return email;
    }
}
