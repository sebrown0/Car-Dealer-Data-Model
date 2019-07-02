/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface EmployeesFinderRepository extends CrudRepository<Employee, Integer>{
	
	/*
	 * @Param deptId: Department ID from which to get list of employees. 	
	 */
	@Query("SELECT e FROM Employee e WHERE e.department.deptId=:deptId")
	List<Employee> findEmployeesByDeptId(int deptId);
}
