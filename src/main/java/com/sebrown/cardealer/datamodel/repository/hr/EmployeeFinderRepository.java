/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface EmployeeFinderRepository extends CrudRepository<Employee, Integer>{
	
	Employee findEmployeeByEmpId(int empId);

	Employee findEmployeeBySsn(String ssn);
	
	@Query("SELECT e FROM Employee e WHERE firstName=:firstName AND lastName=:lastName")
	Employee findByName(String firstName, String lastName);
}
