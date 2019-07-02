/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;

/**
 * @author Steve Brown
 *
 */
public interface RecordEmployeeAbsentService {
	
	/**
	 * 
	 * @param employeeAbsentData: data for the event.
	 * @param strategy: what are we saving: Annual Leave, Sick etc.
	 * @return Employee Attendance entity.
	 */
	EmployeeAttendance recordAbsentEvent(EmployeeAttendanceData employeeAbsentData, StrategySaveAttendance strategy);
}
