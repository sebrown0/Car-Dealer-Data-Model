/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;
import com.sebrown.cardealer.datamodel.repository.hr.AttendanceYearRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAttendanceRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Find how many days annual leave an employee has had for a year.
 */
@RequiredArgsConstructor
public class FindEmployeeAnnualLeave implements StrategyEmployeeAttendanceRecord{

	private final int empId;
	private final short year;
	private final AttendanceYearRepository attendanceYearRepo;
	private final EmployeeAttendanceRepository empAbsentRepo;

	@Override
	public long getDaysAbsentForYear() {
		AttendanceYear attYear = attendanceYearRepo.findByEmployeeIdAndYear(empId, year);
		return empAbsentRepo.numDaysEmployeeHasBeenAbsent(attYear, "Annual Leave", empId);
	}
}
