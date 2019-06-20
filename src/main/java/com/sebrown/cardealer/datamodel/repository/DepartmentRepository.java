/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.Department;

/**
 * @author Steve Brown
 *
 */
public interface DepartmentRepository extends CrudRepository<Department, Long>{
	Department findByDeptName(String name);
}
