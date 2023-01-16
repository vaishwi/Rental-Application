package com.example.rentbuddy.Controller;

import com.example.rentbuddy.PersistenceLayer.EmailPersistenceFactory;
import com.example.rentbuddy.PersistenceLayer.IEmailPersistence;
import com.example.rentbuddy.Model.Email.EmailFactory;
import com.example.rentbuddy.Model.Email.IEmail;
import com.example.rentbuddy.Model.Email.SpringEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmailController {

    private static final String emailSubject = "Email from Rentbuddy Tenant ";
    private final String systemErrorMessage = "Please try again.";

    @GetMapping("/email/{propertyId}")
    public ModelAndView getEmailPage(@PathVariable("propertyId") int propertyId, Model model, HttpServletRequest request) {
        IEmail springEmail = EmailFactory.instance().createEmail();
        IEmailPersistence emailPersistence = EmailPersistenceFactory.instance().createEmailPersistence();

        IEmail emailDetails = springEmail.loadReceiver(propertyId, emailPersistence);

        if (springEmail == null) {
            return new ModelAndView("email", "error", systemErrorMessage);
        } else {
            String emailFrom = (String) request.getSession().getAttribute("userId");
            String emailTo = ((SpringEmail) emailDetails).getEmailTo();
            String avaibility = ((SpringEmail) emailDetails).getAvaibility();

            springEmail = EmailFactory.instance().createEmail(emailFrom, emailTo, avaibility);

            model.addAttribute("email", springEmail);

            return new ModelAndView("email");
        }
    }

    @PostMapping("/email")
    public ModelAndView EmailPage(@ModelAttribute("email") SpringEmail springEmail) {
        String emailFrom = springEmail.getEmailFrom();
        String emailTo = springEmail.getEmailTo();
        String subject = emailSubject + emailFrom;
        String message = springEmail.getMessage();

        System.out.println();
        IEmail.EmailSendResult sendEmailResult = springEmail.sendEmail(emailTo, message, subject);

        IEmailPersistence emailPersistence = EmailPersistenceFactory.instance().createEmailPersistence();
        IEmailPersistence.EmailResult saveEmailresult = springEmail.save(emailFrom, emailTo, message, emailPersistence);

        if (sendEmailResult.equals(IEmail.EmailSendResult.SUCCESS) && saveEmailresult.equals(IEmailPersistence.EmailResult.SUCCESS)) {
            return new ModelAndView("redirect:/tenantHomePage");
        } else {
            return new ModelAndView("/email", "error", systemErrorMessage);
        }
    }
}
