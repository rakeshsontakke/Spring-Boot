package com.emp.tech.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.internal.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.emp.tech.AppConstants;
import com.emp.tech.SbEmployeeCrudApplication;
import com.emp.tech.model.Employee;
import com.emp.tech.model.Response;
import com.emp.tech.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SbEmployeeCrudApplication.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	Response response;
	Employee employee;
	List<Employee> empList;
	
	@Before
	public void setup() {
		response = new Response(AppConstants.SUCCESS, "success", null);
		employee = new Employee(1, "amit", "dev", "pune");
		empList = new ArrayList<>();
		empList.add(employee);
	}
	
	@Test
	public void testGetAlltEmployees() throws Exception {
		response.setResponse(empList);;
		Mockito.when(employeeService.geAlltEmployees()).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployee() throws Exception {
		response.setResponse(employee);;
		Mockito.when(employeeService.getEmployee(1)).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testCreateEmployee() throws Exception {
		response.setResponse(employee);;
		Mockito.when(employeeService.createEmployee(employee)).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(employee))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		response.setResponse(employee);;
		Mockito.when(employeeService.updateEmployee(employee)).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.put("/employees").contentType(MediaType.APPLICATION_JSON).content(asJsonString(employee))).andExpect(status().isOk());
	}
	
	@Test
	public void testRemoveEmployee() throws Exception {
		response.setResponse(employee);;
		Mockito.when(employeeService.getEmployee(1)).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
