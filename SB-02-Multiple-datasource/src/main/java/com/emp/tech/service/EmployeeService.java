package com.emp.tech.service;

import com.emp.tech.employee.model.Employee;
import com.emp.tech.model.Response;

public interface EmployeeService {

	Response geAlltEmployees();

	Response getEmployee(int employeeId);

	Response createEmployee(Employee employee);

	Response updateEmployee(Employee employee);

	Response removeEmployee(int employeeId);

}
