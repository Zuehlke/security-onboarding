package com.zuehlke.zrs.security.controllers;

import com.zuehlke.zrs.security.models.Employee;
import com.zuehlke.zrs.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured("USER")
    Iterable<Employee> index() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(emp -> !emp.getTitle().equals("ADMIN") && !emp.getDisabled())
                .collect(toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Secured("USER")
    Employee findById(@PathVariable String id) {
        String sql = "SELECT * FROM EMPLOYEE WHERE ID = " + id;
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, num) -> new Employee(rs.getLong("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("TITLE"), rs.getBoolean("DISABLED")));
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Secured("USER")
    void add(Employee employee){
        employeeRepository.save( employee);

    }



    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    void delete(long id) {
        employeeRepository.delete(id);
    }
}
