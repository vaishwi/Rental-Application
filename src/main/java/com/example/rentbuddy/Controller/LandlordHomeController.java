package com.example.rentbuddy.Controller;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.Database.MySQLDatabase;
import com.example.rentbuddy.PersistenceLayer.*;
import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.Advertisement.AdvertisementFactory;
import com.example.rentbuddy.Model.Email.EmailFactory;
import com.example.rentbuddy.Model.Email.IEmail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LandlordHomeController {

    private final String systemErrorMessage = "Please try again.";

    @GetMapping("/landlordHomePage")
    public ModelAndView landlordHomePage(Model model, HttpServletRequest request) throws SQLException {
        String landlordId = request.getSession().getAttribute("userId").toString();
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        List<Advertisement> advertisementList = new ArrayList<>();
        Advertisement advertisement = (Advertisement) AdvertisementFactory.instance().createAdvertisement();
        advertisement.getAdvertisements(landlordId, advertisementList, landlordPersistence);
        model.addAttribute("advertisementsList", advertisementList);
        model.addAttribute("userId", landlordId);
        return new ModelAndView("landlordHomePage", "userID", "advertisementsList");
    }

    @GetMapping("/landlordInbox")
    public ModelAndView getInbox(HttpServletRequest request, Model model) {
        String landlordId = (String) request.getSession().getAttribute("userId");
        IEmail email = EmailFactory.instance().createEmail();

        IEmailPersistence emailPersistence = EmailPersistenceFactory.instance().createEmailPersistence();

        List<IEmail> inboxEmails = email.loadLandlordInbox(landlordId, emailPersistence);
        model.addAttribute("userId", landlordId);
        if (inboxEmails == null) {
            return new ModelAndView("inbox", "error", systemErrorMessage);
        } else {
            return new ModelAndView("inbox", "inboxEmails", inboxEmails);
        }

    }
}