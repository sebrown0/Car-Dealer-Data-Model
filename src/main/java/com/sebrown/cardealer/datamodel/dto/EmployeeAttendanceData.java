package com.sebrown.cardealer.datamodel.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Steve Brown
 *
 * Employee Absent DTO.
 */
@Component
@Getter @Setter
@NoArgsConstructor
public class EmployeeAttendanceData {
	private int empId;
	private short yearOfrequest;
	private LocalDate startDate;
	private LocalDate endDate; 
	private String reason;
	
	// Derived fields
	private AttendanceYear attendanceYear;
	private long numDays;
	
	public EmployeeAttendanceData(int empId, LocalDate startDate, LocalDate endDate) {
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.yearOfrequest = (short) startDate.getYear();
	}
}

