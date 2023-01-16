package com.example.rentBuddy.Model.Email;

import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;
import com.example.rentbuddy.Model.Email.IEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpringEmailTest {
    @Test
    public void loadCorrectSenderDetailTest() {

        IEmail email = new SpringIEmailMock();

        IEmailPersistence emailDBMock = new EmailDBMock();
        IEmail emailDeatils = email.loadReceiver(2, emailDBMock);

        Assertions.assertEquals("vaishwi@gmail.com", ((SpringIEmailMock) emailDeatils).getEmailTo());
    }

    @Test
    public void loadSenderDetailsStorageFailureTest() {

        IEmail email = new SpringIEmailMock();
        EmailDBMock emailDBMock = new EmailDBMock();
        IEmail emailDetails = email.loadReceiver(4, emailDBMock);

        Assertions.assertNull(emailDetails);
    }

    @Test
    public void saveUserTest() {
        IEmail email = new SpringIEmailMock();
        EmailDBMock emailDBMock = new EmailDBMock();

        String emailFrom = "hitesh@gmail.com";
        String emailTo = "vaishwipatel@gmail.com";
        String message = "HI, I want to see your home";

        IEmailPersistence.EmailResult result = email.save(emailFrom, emailTo, message, emailDBMock);
        Assertions.assertEquals(IEmailPersistence.EmailResult.SUCCESS, result);

    }

    @Test
    public void loadLandlordEmailsTest() {
        String landlordID = "vaishwipatel82110@gmail.com";

        EmailDBMock emailDB = new EmailDBMock();
        IEmail email = new SpringIEmailMock();

        List<IEmail> inboxEmails = email.loadLandlordInbox(landlordID, emailDB);
        Assertions.assertEquals(2, inboxEmails.size());
    }

    @Test
    public void loadLandlordEmailsErrorTest() {

        String landlordID = "ka@gmail.com";
        EmailDBMock emailDB = new EmailDBMock();

        IEmail email = new SpringIEmailMock();
        List<IEmail> inboxEmails = email.loadLandlordInbox(landlordID, emailDB);
        Assertions.assertNull(inboxEmails);

    }

    @Test
    public void loadLandlordEmailsNoEMailTest() {

        String landlordID = "kaya@gmail.com";
        EmailDBMock emailDB = new EmailDBMock();
        IEmail email = new SpringIEmailMock();
        List<IEmail> inboxEmails = email.loadLandlordInbox(landlordID, emailDB);
        Assertions.assertEquals(0, inboxEmails.size());

    }

    @Test
    public void sendEmailTest() {
        IEmail email = new SpringIEmailMock();
        String emailTo = "vaishwi@gmail.com";
        String message = "HI";
        String subject = "Eamil from rentbuddy";
        IEmail.EmailSendResult result = email.sendEmail(emailTo, message, subject);
        Assertions.assertEquals(IEmail.EmailSendResult.SUCCESS, result);
    }

    @Test
    public void sendMailFailureTest(){
        assertThrows(MessagingException.class,() -> {
            Session session = null;
            MimeMessage message = new MimeMessage(session);
            message.setSubject(null);
            Transport.send(message);
        });
    }

}
