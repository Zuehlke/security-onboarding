package com.zuehlke.zrs.security;

import com.zuehlke.zrs.security.models.City;
import com.zuehlke.zrs.security.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by nesp on 21-Sep-16.
 */
@Component
public class Bootstrapper {

    private CityRepository cityRepository;

    @Autowired
    public Bootstrapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void onStartup() {
        Arrays.asList(
                new City("Belgrade", "Serbia"),
                new City("Novi Sad", "Serbia"),
                new City("Zagreb", "Croatia"),
                new City("Nis", "Serbia")
        ).forEach(cityRepository::save);

    }
}
