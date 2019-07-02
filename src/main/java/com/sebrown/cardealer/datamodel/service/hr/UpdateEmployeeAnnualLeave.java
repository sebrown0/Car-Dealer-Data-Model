/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.util.List;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;
import com.sebrown.cardealer.datamodel.repository.hr.AttendanceYearRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAttendanceRepository;
import com.sebrown.cardealer.datamodel.repository.hr.RoleAndSeniorityRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Update an employee's annual leave record with a new request for leave.
 */
@RequiredArgsConstructor
public class UpdateEmployeeAnnualLeave implements StrategySaveAttendance{

	private static final String REASON = "Annual Leave";
	
	private final AttendanceYearRepository attYearRepo;
	private final EmployeeAttendanceRepository empAttRepo;
	private final RoleAndSeniorityRepository rasRepo;
	
	private EmployeeAttendance newEmpAttendanceRecord = null;
	private EmployeeAttendanceData empAttData;
		
	@Override
	public EmployeeAttendance recordEmployeeAbsent(EmployeeAttendanceData employeeAttData) {
		this.empAttData = employeeAttData; 
		if(checkAnnualLeaveRequest(this.empAttData.getEmpId())) {
			this.empAttData.setReason(REASON);
			newEmpAttendanceRecord = AttendanceRecordMapper.map(this.empAttData);
			empAttRepo.save(newEmpAttendanceRecord);
		}
		return newEmpAttendanceRecord;
	}
			
	/*
	 * Calculate if the requested amount of annual leave exceeds allowance.
	 * Return true if request OK. 
	 */
	public boolean checkAnnualLeaveRequest(int empId) {
		if(employeeDoesNotHaveAttendanceRecordForThisYear(empId)) {
			return annualLeaveApproved(0, empId);
		}
		return checkEmployeesAnnualLeaveRecordForYear(empId);
	}
	
	/*
	 * Check to see if the employee has a record for this year.
	 * If the employee does not have a record the annual leave request can be granted.
	 */
	private boolean employeeDoesNotHaveAttendanceRecordForThisYear(int empId) {
		List<EmployeeAttendance> empAtt = 
				empAttRepo.findEmployeeAttendanceRecordForYear(empAttData.getAttendanceYear().getYear(), empId);
		return (empAtt.isEmpty()) ? true : false;
	}
	
	/*
	 * Find out how many days annual leave the employee has taken for the year.
	 * If the number of days requested are greater than those remaining, deny request. 
	 */
	private boolean checkEmployeesAnnualLeaveRecordForYear(int empId) {
		long annualLeaveTaken = 
				new ContextEmployeeAttendanceRecord(
						new FindEmployeeAnnualLeave(empId, empAttData.getYearOfrequest(), 
								attYearRepo, empAttRepo)).getDaysAbsent();
		return annualLeaveApproved(annualLeaveTaken, empId);
	}
	
	/*
	 * Check the requested annual leave against remaining entitlememt.
	 */
	private boolean annualLeaveApproved(long annualLeaveTaken, int empId) {
		long annualLeave = rasRepo.findSeniority(empId).getHolidayEntitlement();
		long diff = (annualLeave - annualLeaveTaken) - empAttData.getNumDays();
		return (diff >= 0 && diff <= annualLeave ) ? true : false;
	}
}
