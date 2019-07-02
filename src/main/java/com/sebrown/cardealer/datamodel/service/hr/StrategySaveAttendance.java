/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;

/**
 * @author Steve Brown
 *
 *	Interface for a strategy to save an employee being absent i.e the reason.
 */
public interface StrategySaveAttendance {
	
	/**
	 * Record an employee being absent for a strategy.
	 * 
	 * @param employeeAttData:  Data for absent event.
	 * @return Employee Attendance entity.
	 */
	EmployeeAttendance recordEmployeeAbsent(EmployeeAttendanceData employeeAttData);
}
