package com.sebrown.cardealer.datamodel.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Steve Brown
 *
 * Employee DTO.
 */
@Getter @Setter 
@AllArgsConstructor
@Builder
public class EmployeeDto {
	
	private int empId;
	private int deptId;
	private String firstName;
	private String lastName;
	private String deptName;
	private String roleName;
	private String seniority;
	private String ssn;
	private BigDecimal salary;
	private short annualLeave;
	private Calendar dob;
	private Calendar hireDate;
}

