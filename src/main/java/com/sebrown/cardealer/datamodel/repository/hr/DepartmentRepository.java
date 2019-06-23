/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.hr.Department;

/**
 * @author Steve Brown
 *
 */
public interface DepartmentRepository extends CrudRepository<Department, Long>{
	Department findByDeptName(String name);
}
