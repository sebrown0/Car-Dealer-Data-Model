/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.AttendanceYearRepository;

import lombok.AllArgsConstructor; 

/**
 * @author Steve Brown
 *
 *	Get the Absent Year tuple for an employee for a specified year.
 *	If there is no tuple i.e. the employee has not been absent that year, 
 *	then create one.
 */
@Transactional
@Component
@AllArgsConstructor
public class SetEmployeeAttendanceYearImpl implements SetEmployeeAttendanceYear {

	private final AttendanceYearRepository absYearRepo;
		
	/**
	 * Find the year for an employee's attendance.
	 * If the employee has no attendance record for the year, create one.
	 * 
	 * @param empFinder: strategy for finding the employee.
	 * @param year: the year to look for. 
	 */
	@Override
	public AttendanceYear setAttendanceYear(StrategyEmployeeFinder empFinder, LocalDate year) {
		Employee emp = empFinder.findEmployee();
		AttendanceYear absentYear = absYearRepo.findByEmployeeIdAndYear(emp.getEmpId(), (short)year.getYear());
		if(absentYear == null) {
			absentYear = createAttendanceYear(emp, (short)year.getYear());
		}
		return absentYear;
	}			

	/*
	 * There's not a record for the employee, for this year, so create one.
	 */
	private AttendanceYear createAttendanceYear(Employee emp, short year) {
		AttendanceYear attYear = new AttendanceYear();
		attYear.setEmployee(emp);
		attYear.setYear(year);
		absYearRepo.save(attYear);
		return attYear;
	}
}
