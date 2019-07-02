/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;
import com.sebrown.cardealer.datamodel.util.GenericBuilder;

/**
 * @author Steve Brown
 *
 * Map an Employee Attendance DTO to an Employee Attendance Entity
 */
public class AttendanceRecordMapper {
	
	public static EmployeeAttendance map(EmployeeAttendanceData empAttData) {
	
		return GenericBuilder.of(EmployeeAttendance::new)
			.with(EmployeeAttendance::setEmpAbsentId, empAttData.getAttendanceYear().getAbsentId())
			.with(EmployeeAttendance::setEmpId, empAttData.getEmpId())
			.with(EmployeeAttendance::setAbsentStartDate, empAttData.getStartDate())
			.with(EmployeeAttendance::setAbsentEndDate, empAttData.getEndDate())
			.with(EmployeeAttendance::setAbsentYear, empAttData.getAttendanceYear())
			.with(EmployeeAttendance::setNumDays, empAttData.getNumDays())
			.with(EmployeeAttendance::setReason, empAttData.getReason())
			.build();
	}
}
