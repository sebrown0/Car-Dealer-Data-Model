/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.hr.AbsentYear;

/**
 * @author Steve Brown
 *
 */
public interface AbsentYearRepository extends CrudRepository<AbsentYear, Integer>{
	
	@Query("SELECT absYr FROM AbsentYear absYr WHERE absYr.employee.empId=:empId AND absYr.year=:year")
	AbsentYear findByEmployeeIdAndYear(int empId, short year);
}
