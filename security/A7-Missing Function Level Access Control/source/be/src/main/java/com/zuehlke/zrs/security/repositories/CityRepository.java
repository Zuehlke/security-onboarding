package com.zuehlke.zrs.security.repositories;

import com.zuehlke.zrs.security.models.City;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CityRepository extends Repository<City, Long> {

    City save(City entity);

    List<City> findAll();

    City findByNameAndStateAllIgnoringCase(String name, String state);



}
