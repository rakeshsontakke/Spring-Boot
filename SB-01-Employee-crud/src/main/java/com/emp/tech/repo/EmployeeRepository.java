package com.emp.tech.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emp.tech.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
