/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.Department;
import com.sebrown.cardealer.datamodel.service.hr.DepartmentManager;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	
	Department findByDeptName(String name);
	
	@Query (value = 
		" SELECT d.dept_name AS departmentName, e.manager_id AS managerId, e.first_name AS firstName, e.last_name AS lastName" + 
		" FROM employee e" +  
		" INNER JOIN department d ON d.dept_id = e.dept_id" +  
		" WHERE e.dept_id = ?1 AND e.emp_id = e.manager_id", nativeQuery = true)
	DepartmentManager findDeptManager(int deptId);	
}
