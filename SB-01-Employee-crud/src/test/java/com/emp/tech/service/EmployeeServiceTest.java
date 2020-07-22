package com.emp.tech.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.tech.AppConstants;
import com.emp.tech.SbEmployeeCrudApplication;
import com.emp.tech.model.Employee;
import com.emp.tech.model.Response;
import com.emp.tech.repo.EmployeeRepository;
import com.emp.tech.service.impl.EmployeeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SbEmployeeCrudApplication.class)
public class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepositoryMock;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;	

	Employee employee;
	List<Employee> empList;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	@Before
	public void setup() {
		employee = new Employee(1, "amit", "dev", "pune");
		empList = new ArrayList<>();
		empList.add(employee);
		
	}
	
	@Test
	public void testGeAlltEmployees() {
		
		Mockito.when(employeeRepositoryMock.findAll()).thenReturn(empList);
		Response resp = employeeService.geAlltEmployees();
		assertEquals(AppConstants.SUCCESS, resp.getStatus());
	
	}
	
	@Test
	public void testGetEmployee() {
		
		Response resp;
		Optional<Employee> employeeOpt;
		employeeOpt = Optional.ofNullable(employee);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		resp = employeeService.getEmployee(1);
		assertEquals(AppConstants.SUCCESS, resp.getStatus());
	
		employeeOpt = Optional.ofNullable(null);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		resp = employeeService.getEmployee(1);
		assertEquals(AppConstants.FAILURE, resp.getStatus());
	}

	@Test
	public void testCreateEmployee() {
		
		Response resp;
		Mockito.when(employeeRepositoryMock.save(employee)).thenReturn(employee);
		resp = employeeService.createEmployee(employee);
		assertEquals(AppConstants.SUCCESS, resp.getStatus());
	}
	
	@Test
	public void testUpdateEmployee() {
		
		Response resp;
		Optional<Employee> employeeOpt;
		employeeOpt = Optional.ofNullable(employee);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		Mockito.when(employeeRepositoryMock.save(employee)).thenReturn(employee);
		resp = employeeService.updateEmployee(employee);
		assertEquals(AppConstants.SUCCESS, resp.getStatus());
		
		employeeOpt = Optional.ofNullable(null);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		resp = employeeService.updateEmployee(employee);
		assertEquals(AppConstants.FAILURE, resp.getStatus());
	}
	
	@Test
	public void testRemoveEmployee() {
		
		Response resp;
		Optional<Employee> employeeOpt;
		employeeOpt = Optional.ofNullable(employee);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		Mockito.when(employeeRepositoryMock.save(employee)).thenReturn(employee);
		resp = employeeService.removeEmployee(1);
		assertEquals(AppConstants.SUCCESS, resp.getStatus());
		
		employeeOpt = Optional.ofNullable(null);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(employeeOpt);
		resp = employeeService.removeEmployee(1);
		assertEquals(AppConstants.FAILURE, resp.getStatus());
	}
}
