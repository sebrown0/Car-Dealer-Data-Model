/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.hr.AbsentYear;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAbsent;

/**
 * @author Steve Brown
 *
 */
public interface EmployeeAbsentRepository extends CrudRepository<EmployeeAbsent, Long>{
	
	@Query("SELECT COALESCE(sum(e.numDays), 0)" 
			+ " FROM EmployeeAbsent e" 
			+ " WHERE e.absentYear=:absentYear" 
			+ " AND e.reason=:reason"
			+ " AND e.absentYear.employee.empId=:empId")
	long numDaysEmployeeHasBeenAbsent(AbsentYear absentYear, String reason, int empId);
	
	@Query("SELECT e" 
			+ " FROM EmployeeAbsent e" 
			+ " WHERE e.absentYear.year=:year"
			+ " AND e.absentYear.employee.empId=:empId")
	List<EmployeeAbsent> findEmployeeAbsentRecordsForYear(short year, int empId);
}
