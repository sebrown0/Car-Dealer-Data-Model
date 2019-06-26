package com.sebrown.cardealer.datamodel.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Steve Brown
 *
 * Employee DTO.
 */
@Data
@Builder
public class EmployeeDto {
	
	private int empId;
	private int deptId;
	private Integer managerId;
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

