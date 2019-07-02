/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface AttendanceYearRepository extends CrudRepository<AttendanceYear, Integer>{
	
	@Query("SELECT attYr FROM AttendanceYear attYr WHERE attYr.employee.empId=:empId AND attYr.year=:year")
	AttendanceYear findByEmployeeIdAndYear(int empId, short year);
}
