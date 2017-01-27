package com.zuehlke.zrs.security;

import com.zuehlke.zrs.security.models.Employee;
import com.zuehlke.zrs.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        List<Employee> employees = Arrays.asList(
                new Employee("Name1", "Lastname1", "Expert Software Engineer"),
                new Employee("Name2", "Lastname2", "Software Engineer"),
                new Employee("Name3", "Lastname3", "Junior Software Engineer"),
                new Employee("Name11", "Lastname11", "Junior Software Engineer"),
                new Employee("Name4", "Lastname4", "Trainee"),
                new Employee("Name5", "Lastname5", "Trainee"),
                new Employee("Name9", "Lastname9", "Junior Software Engineer")
        );
        employees.forEach(emp -> emp.setJmbg(
                Long.toString(ThreadLocalRandom.current().nextLong(1_000_000_000_000L, 9_999_999_999_999L)))
        );
        employees.forEach(employeeRepository::save);

    }
}
