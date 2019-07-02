/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface EmployeeAttendanceRepository extends CrudRepository<EmployeeAttendance, Long>{
	
	@Query("SELECT COALESCE(sum(e.numDays), 0)" 
			+ " FROM EmployeeAttendance e" 
			+ " WHERE e.absentYear=:absentYear" 
			+ " AND e.reason=:reason"
			+ " AND e.absentYear.employee.empId=:empId")
	long numDaysEmployeeHasBeenAbsent(AttendanceYear absentYear, String reason, int empId);
	
	@Query("SELECT e" 
			+ " FROM EmployeeAttendance e" 
			+ " WHERE e.absentYear.year=:year"
			+ " AND e.absentYear.employee.empId=:empId")
	List<EmployeeAttendance> findEmployeeAttendanceRecordForYear(short year, int empId);
}
