package com.example.rentbuddy.Controller;


import com.example.rentbuddy.Database.IDatabase;
import com.example.rentbuddy.PersistenceLayer.IPropertyPersistence;
import com.example.rentbuddy.Database.MySQLDatabase;
import com.example.rentbuddy.PersistenceLayer.PropertyDB;
import com.example.rentbuddy.Model.Advertisement.FilterAdvertisement;
import com.example.rentbuddy.Model.Advertisement.FilterAdvertisementFactory;
import com.example.rentbuddy.Model.Property.PropertyResult;
import com.example.rentbuddy.Model.Property.PropertyResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TenantHomeController {

    @GetMapping("/tenantHomePage")
    public String tenantHomePage() {
        return "tenantHomePage";
    }

    @PostMapping("/search")
    public String tenantHomePageSearch(@ModelAttribute FilterAdvertisement tenant, HttpSession session, ModelMap modelMap) {

        IDatabase database = MySQLDatabase.instance();
        IPropertyPersistence propertyPersistence = new PropertyDB(database);
        FilterAdvertisement property = (FilterAdvertisement) FilterAdvertisementFactory.instance().create();
        List<FilterAdvertisement> propertyResults = new ArrayList<>();
        property.getProperties(tenant.getKeyword(), propertyResults, propertyPersistence);
        modelMap.addAttribute("properties", propertyResults);
        return "tenantResultPage";
    }

    @PostMapping("/filter")
    public String tenantHomePageFilter(@ModelAttribute FilterAdvertisement tenant, HttpSession session, ModelMap modelMap) {

        IDatabase database = MySQLDatabase.instance();
        IPropertyPersistence propertyPersistence = new PropertyDB(database);
        FilterAdvertisement property = (FilterAdvertisement) FilterAdvertisementFactory.instance().create();
        List<FilterAdvertisement> propertyResults = new ArrayList<>();
        property.filterProperties(tenant, propertyResults, propertyPersistence);
        modelMap.addAttribute("properties", propertyResults);
        return "tenantResultPage";
    }

    @GetMapping("/viewproperty/{id}")

    public ModelAndView tenantviewProprties(@PathVariable("id") int propertyId, HttpSession session, ModelMap modelMap) {
        IDatabase database = MySQLDatabase.instance();
        IPropertyPersistence propertyPersistence = new PropertyDB(database);
        PropertyResult property = (PropertyResult) PropertyResultFactory.instance().create();
        property.getViewProperties(propertyId, property, propertyPersistence);

        modelMap.addAttribute("properties", property);
        return new ModelAndView("tenantViewProperty");

    }

}
