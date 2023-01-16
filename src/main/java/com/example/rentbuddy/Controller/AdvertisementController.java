package com.example.rentbuddy.Controller;

import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.PersistenceLayer.ILandlordPersistence;
import com.example.rentbuddy.PersistenceLayer.LandlordDB;
import com.example.rentbuddy.Database.MySQLDatabase;
import com.example.rentbuddy.Model.Advertisement.Advertisement;
import com.example.rentbuddy.Model.Advertisement.AdvertisementFactory;
import com.example.rentbuddy.Model.ImageConversion.IImageConversion;
import com.example.rentbuddy.Model.ImageConversion.ImageConversionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Controller
public class AdvertisementController {
    @GetMapping("/advertisementPosting")
    public String postAdvertisement(Model model) {
        Advertisement advertisement = (Advertisement) AdvertisementFactory.instance().createAdvertisement();
        model.addAttribute("advertisement", advertisement);
        return "landlordAdvertisementPosting";
    }

    @PostMapping("/advertisementPosting")
    public ModelAndView submitAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement, HttpServletRequest request) throws IOException {
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        advertisement.setUserID((String) request.getSession().getAttribute("userId"));
        advertisement.addAdvertisement(advertisement, landlordPersistence);
        advertisement.setDisplayPhoto(Base64.getEncoder().encodeToString(advertisement.getPhotos().getBytes()));
        return new ModelAndView("showLandlordPosts", "advertisement", advertisement);
    }

    @GetMapping("/showLandlordPosts")
    public ModelAndView showAdvertisement(@RequestParam("advertisement") Advertisement advertisement) {
        ModelAndView mv = new ModelAndView("showLandlordPosts");
        mv.addObject("advertisement", advertisement);
        return mv;
    }

    @PostMapping("/showLandlordPosts")
    public ModelAndView showAdvertisementPost(@ModelAttribute("advertisement") Advertisement advertisement, HttpServletRequest request) {
        advertisement.setUserID(request.getSession().getAttribute("userId").toString());
        return new ModelAndView("redirect:/landlordHomePage", "userID", advertisement.getUserID());
    }

    @GetMapping("/updateAdvertisement/{id}")
    public ModelAndView editAdvertisement(@PathVariable(value = "id") String advertisementID, Model model, HttpServletRequest request) {
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        IImageConversion imageConverter = ImageConversionFactory.instance().createImageConverter();
        Advertisement advertisement = (Advertisement) AdvertisementFactory.instance().createAdvertisement();
        advertisement.getDetails(Integer.parseInt(advertisementID), landlordPersistence, imageConverter);
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("userID", request.getSession().getAttribute("userId").toString());
        model.addAttribute("advertisementID", Integer.valueOf(advertisementID));
        return new ModelAndView("updateAdvertisement", "advertisement", advertisement);
    }

    @PostMapping("/updateAdvertisement")
    public ModelAndView updateAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement, HttpServletRequest request) {
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        advertisement.setUserID(request.getSession().getAttribute("userId").toString());
        advertisement.editAdvertisement(advertisement, landlordPersistence);
        return new ModelAndView("redirect:/landlordHomePage", "userID", advertisement.getUserID());
    }

    @GetMapping("/deleteAdvertisement/{id}")
    public ModelAndView deleteAdvertisement(@PathVariable("id") String advertisementID, Model model, HttpServletRequest request) {
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        IImageConversion imageConverter = ImageConversionFactory.instance().createImageConverter();
        Advertisement advertisement = (Advertisement) AdvertisementFactory.instance().createAdvertisement();
        advertisement.getDetails(Integer.parseInt(advertisementID), landlordPersistence, imageConverter);
        advertisement.setUserID(request.getSession().getAttribute("userId").toString());
        advertisement.deleteAdvertisement(Integer.parseInt(advertisementID), landlordPersistence);
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("advertisementID", Integer.valueOf(advertisementID));
        return new ModelAndView("redirect:/landlordHomePage");
    }

    @GetMapping("/deleteAll/{id}")
    public ModelAndView deleteAllAdvertisement(@PathVariable("id") String landlordId, HttpServletRequest request) {
        IDatabase database = MySQLDatabase.instance();
        ILandlordPersistence landlordPersistence = new LandlordDB(database);
        Advertisement advertisement = (Advertisement) AdvertisementFactory.instance().createAdvertisement();
        advertisement.deleteAllAdvertisements(request.getSession().getAttribute("userId").toString(), landlordPersistence);
        return new ModelAndView("redirect:/landlordHomePage");
    }
}
