/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.EmployeeAbsent;

/**
 * @author Steve Brown
 *
 */
public interface EmployeeAbsentRepository extends CrudRepository<EmployeeAbsent, Long>{
	
	@Query("SELECT SUM(e.numDays)" 
			+ " FROM EmployeeAbsent e" 
			+ " WHERE e.absentYear.year=:year" 
			+ " AND e.absentYear.employee.empId=:empId"
			+ " AND e.reason=:reason")
	long numDaysEmployeeHasBeenAbsent(short year, int empId, String reason);
	
	@Query("SELECT e" 
			+ " FROM EmployeeAbsent e" 
			+ " WHERE e.absentYear.year=:year" 
			+ " AND e.absentYear.employee.empId=:empId")
	List<EmployeeAbsent> findEmployeeAbsentRecordsForYear(short year, int empId);
}
