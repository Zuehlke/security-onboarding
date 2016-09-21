package com.zuehlke.zrs.security.controllers;

import com.zuehlke.zrs.security.models.City;
import com.zuehlke.zrs.security.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nesp on 21-Sep-16.
 */
@Controller
@EnableAutoConfiguration
public class HomeController {

    private CityRepository cityRepository;

    @Autowired
    public HomeController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    Iterable<City> home() {
        return  cityRepository.findAll();
    }

}
