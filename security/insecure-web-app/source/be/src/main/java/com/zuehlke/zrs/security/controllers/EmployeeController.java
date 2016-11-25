package com.zuehlke.zrs.security.controllers;

import com.zuehlke.zrs.security.models.Employee;
import com.zuehlke.zrs.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nesp on 21-Sep-16.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured("USER")
    Iterable<Employee> index() {
        return  employeeRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.DELETE)
    //@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    void delete(long id) {
        employeeRepository.delete(id);
    }
}
