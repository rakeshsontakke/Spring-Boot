package com.emp.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.tech.employee.model.Employee;
import com.emp.tech.model.Response;
import com.emp.tech.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public Response geAlltEmployees() {

		log.info("Entered in geAlltEmployees method of EmployeeController");
		Response resp = null;
		resp = employeeService.geAlltEmployees();
		log.info("Left from geAlltEmployees method of EmployeeController");
		return resp;
	}

	@GetMapping("/employees/{id}")
	public Response getEmployee(@PathVariable("id") int employeeId) {
		
		log.info("Entered in getEmployee method of EmployeeController");
		Response resp = null;
		resp = employeeService.getEmployee(employeeId);
		log.info("Left from getEmployee method of EmployeeController");
		return resp;
	}

	@PostMapping("/employees")
	public Response createEmployee(@RequestBody Employee employee) {

		log.info("Entered in createEmployee method of EmployeeController");
		Response resp = null;
		resp = employeeService.createEmployee(employee);
		log.info("Left from createEmployee method of EmployeeController");
		return resp;
	}

	@PutMapping("/employees")
	public Response updateEmployee(@RequestBody Employee employee) {

		log.info("Entered in updateEmployee method of EmployeeController");
		Response resp = null;
		resp = employeeService.updateEmployee(employee);
		log.info("Left from updateEmployee method of EmployeeController");
		return resp;
	}

	@DeleteMapping("/employees/{id}")
	public Response removeEmployee(@PathVariable("id") int employeeId) {

		log.info("Entered in removeEmployee method of EmployeeController");
		Response resp = null;
		resp = employeeService.removeEmployee(employeeId);
		log.info("Left from removeEmployee method of EmployeeController");
		return resp;
	}
}
