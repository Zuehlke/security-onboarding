package com.zuehlke.zrs.security.controllers;

import com.zuehlke.zrs.security.models.Employee;
import com.zuehlke.zrs.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

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
    Iterable<Employee> index() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(emp -> !emp.getTitle().equals("ADMIN") && !emp.getDisabled())
                .collect(toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    Employee findById(@PathVariable String id) {
        return employeeRepository.findOne(Long.parseLong(id));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee not found")
    public void notFound() {
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    void delete(@PathVariable long id) {
        employeeRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    Employee add(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
