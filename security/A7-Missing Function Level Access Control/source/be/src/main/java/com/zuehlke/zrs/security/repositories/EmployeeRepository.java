package com.zuehlke.zrs.security.repositories;

import com.zuehlke.zrs.security.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
