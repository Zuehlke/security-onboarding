package com.zuehlke.zrs.security;

import com.zuehlke.zrs.security.models.City;
import com.zuehlke.zrs.security.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
        cityRepository.save(new City("Belgrade", "Serbia"));
    }
}
