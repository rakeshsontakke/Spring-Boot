package com.emp.tech.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.tech.AppConstants;
import com.emp.tech.model.Employee;
import com.emp.tech.model.Response;
import com.emp.tech.repo.EmployeeRepository;
import com.emp.tech.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Response geAlltEmployees() {

		Response resp = null;
		List<Employee> employees = null;
		employees = (List<Employee>) employeeRepository.findAll();
		resp = new Response(AppConstants.SUCCESS, "successfully fetched", employees);
		return resp;
	}

	@Override
	public Response getEmployee(int employeeId) {
		
		Response resp = null;
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		if(employeeOpt.isPresent()) {
			resp = new Response(AppConstants.SUCCESS, "successfully fetched", employeeOpt.get());
		} else {
			resp = new Response(AppConstants.FAILURE, "Emp not exist", null);
		}
		return resp;
	}

	@Override
	public Response createEmployee(Employee employee) {

		Response resp = null;
		employee = employeeRepository.save(employee);
		resp = new Response(AppConstants.SUCCESS, "successfully created", employee);
		return resp;
	}

	@Override
	public Response updateEmployee(Employee employee) {

		Response resp = null;
		Optional<Employee> employeeOpt = employeeRepository.findById(employee.getEmployeeId());
		if(employeeOpt.isPresent()) {
			employee = employeeRepository.save(employee);
			resp = new Response(AppConstants.SUCCESS, "successfully updated", employee);
		} else {
			resp = new Response(AppConstants.FAILURE, "Emp not exist", null);
		}
		
		return resp;
	}

	@Override
	public Response removeEmployee(int employeeId) {
		
		Response resp = null;
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		if(employeeOpt.isPresent()) {
			employeeRepository.deleteById(employeeId);
			resp = new Response(AppConstants.SUCCESS, "successfully deleted", employeeOpt.get());
		} else {
			resp = new Response(AppConstants.FAILURE, "Emp not exist", null);
		}
		return resp;
	}

}
