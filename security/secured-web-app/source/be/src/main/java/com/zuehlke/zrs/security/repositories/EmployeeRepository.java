package com.zuehlke.zrs.security.repositories;

import com.zuehlke.zrs.security.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
