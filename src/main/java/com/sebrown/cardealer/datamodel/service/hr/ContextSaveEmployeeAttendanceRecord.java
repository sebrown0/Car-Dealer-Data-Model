/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 *  Context of an Employee Attendance Record, depending on the save strategy.
 */
@RequiredArgsConstructor
public class ContextSaveEmployeeAttendanceRecord {

	private final StrategySaveAttendance strategy;
	private final EmployeeAttendanceData employeeAttData;
	
	public EmployeeAttendance update() {
		return strategy.recordEmployeeAbsent(employeeAttData);
	}
}
