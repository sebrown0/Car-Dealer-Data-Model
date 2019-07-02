/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.time.LocalDate;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;

/**
 * @author Steve Brown
 *
 *  
 */
public interface SetEmployeeAttendanceYear {
	/**
	 * Set the AttendanceYear entity to a found tuple.
	 * If no tuple found then create on for that year.
	 * 
	 * @param emp: strategy for finding the employee.
	 * @param year: the year to look for.
	 * @return Attendance Year entity for params.
	 */
	AttendanceYear setAttendanceYear(StrategyEmployeeFinder emp, LocalDate year);
}
