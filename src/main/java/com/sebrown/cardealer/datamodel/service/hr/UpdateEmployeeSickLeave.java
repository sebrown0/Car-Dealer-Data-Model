/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAttendanceRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Set the reason for being absent as 'Sick'.
 * 
 * Leaves room for extension if required i.e. check how many days sick
 * leave an employee has had and inform manager if necessary.
 */
@RequiredArgsConstructor
public class UpdateEmployeeSickLeave implements StrategySaveAttendance{

	private static final String REASON = "Sick";
	
	private final EmployeeAttendanceRepository empAttRepo;
	
	private EmployeeAttendance newEmpAttendanceRecord = null;
	private EmployeeAttendanceData empAttData;	

	@Override
	public EmployeeAttendance recordEmployeeAbsent(EmployeeAttendanceData employeeAttData) {
		this.empAttData = employeeAttData; 
		this.empAttData.setReason(REASON);
		newEmpAttendanceRecord = AttendanceRecordMapper.map(this.empAttData);
		empAttRepo.save(newEmpAttendanceRecord);
		return newEmpAttendanceRecord;
	}
	
}
