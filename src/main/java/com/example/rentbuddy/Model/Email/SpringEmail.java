package com.example.rentbuddy.Model.Email;

import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SpringEmail implements IEmail {
    private String message;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String avaibility;
    private JavaMailSender javaMailSender = null;
    private String emailUsername = "";
    private String emailPassword = "";
    private String emailHost = "";
    private String emailPort = "";

    private Map<String, String> mailSenderProperties;

    public SpringEmail() {
        getEmailServiceVariableFromEnvironment();
        setMailSenderProperties();
        javaMailSender = setUpJavaMailSender();
    }

    public SpringEmail(String emailTo, String avaibility) {
        this.emailTo = emailTo;
        this.avaibility = avaibility;
    }

    public SpringEmail(String emailFrom, String emailTo, String avaibility) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.avaibility = avaibility;
    }

    private void setMailSenderProperties() {

        mailSenderProperties = new HashMap<String, String>();
        mailSenderProperties.put("mail.transport.protocol", "smtp");
        mailSenderProperties.put("mail.smtp.auth", "true");
        mailSenderProperties.put("mail.smtp.starttls.enable", "true");
        mailSenderProperties.put("mail.debug", "true");

    }

    private void getEmailServiceVariableFromEnvironment() {
        this.emailHost = System.getenv("emailHost");
        this.emailUsername = System.getenv("emailUsername");
        this.emailPassword = System.getenv("emailPassword");
        this.emailPort = System.getenv("emailPort");
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
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(this.emailUsername);
            mailMessage.setTo(emailTo);
            mailMessage.setText(message);
            mailMessage.setSubject(subject);

            javaMailSender.send(mailMessage);

            return EmailSendResult.SUCCESS;
        } catch (Exception e) {
            return EmailSendResult.FAILURE;
        }
    }

    private JavaMailSender setUpJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailHost);
        mailSender.setPort(Integer.parseInt(this.emailPort));

        mailSender.setUsername(this.emailUsername);
        mailSender.setPassword(this.emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        for (Map.Entry<String, String> prop : this.mailSenderProperties.entrySet()) {
            props.put(prop.getKey(), prop.getValue());
        }
        return mailSender;
    }

    public String getAvaibility() {
        return avaibility;
    }

    public void setAvaibility(String avaibility) {
        this.avaibility = avaibility;
    }


    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
