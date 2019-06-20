/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.Employee;

/**
 * @author Steve Brown
 *
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

	Employee findEmployeeBySsn(String ssn);
	
	@Query("SELECT e FROM Employee e WHERE firstName=:firstName AND lastName=:lastName")
	Employee findByName(String firstName, String lastName);
	
	@Query("SELECT e FROM Employee e WHERE e.department.dept_id=:deptId")
	List<Employee> findEmployeesForDeptId(int deptId);
	
}
