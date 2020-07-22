package com.emp.tech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="employee")
public class Employee {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue// default auto
	@Column(name="employeeId")
	int employeeId;
	
	@Column(name="employeeName")
	String employeeName;
	
	@Column(name="designation")
	String designation;
	
	@Column(name="location")
	String location;
}
