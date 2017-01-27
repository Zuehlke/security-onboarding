package com.zuehlke.zrs.security;

import com.zuehlke.zrs.security.models.Employee;
import com.zuehlke.zrs.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by nesp on 21-Sep-16.
 */
@Component
class Bootstrapper {

    private EmployeeRepository employeeRepository;

    @Autowired
    public Bootstrapper(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    void onStartup() {
        Arrays.asList(
                new Employee("Name1", "Lastname1", "Expert Software Engineer", false),
                new Employee("Name2", "Lastname2", "Software Engineer", false),
                new Employee("Name3", "Lastname3", "Junior Software Engineer", false),
                new Employee("Name11", "Lastname11", "Junior Software Engineer", false),
                new Employee("Name4", "Lastname4", "Trainee", false),
                new Employee("Name5", "Lastname5", "Trainee", false),
                new Employee("Name9", "Lastname9", "Junior Software Engineer", false),
                new Employee("Name10", "Lastname10", "Trainee", false),
                new Employee("Name12", "Lastname12", "Expert Software Engineer", true),
                new Employee("Name13", "Lastname13", "Expert Software Engineer", true),
                new Employee("Name14", "Lastname14", "Trainee", true),
                new Employee("Name15", "Lastname15", "Expert Software Engineer", true),
                new Employee("Name16", "Lastname6", "Junior Software Engineer", true)
        ).forEach(employeeRepository::save);

    }
}
