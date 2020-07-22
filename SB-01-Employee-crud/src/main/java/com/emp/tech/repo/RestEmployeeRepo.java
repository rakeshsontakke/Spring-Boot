package com.emp.tech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.emp.tech.model.Employee;

@RepositoryRestResource(collectionResourceRel = "repo-employee", path = "repo-employee")
public interface RestEmployeeRepo extends JpaRepository<Employee, Integer>{

}
