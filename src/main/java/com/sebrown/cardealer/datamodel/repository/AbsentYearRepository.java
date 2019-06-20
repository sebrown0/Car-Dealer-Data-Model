/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.AbsentYear;
import com.sebrown.cardealer.datamodel.model.Employee;

/**
 * @author Steve Brown
 *
 */
public interface AbsentYearRepository extends CrudRepository<AbsentYear, Long>{
	AbsentYear findByEmployeeAndYear(Employee emp, short year);
}
