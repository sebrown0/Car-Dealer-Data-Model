/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.hr.AbsentYear;
import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 */
public interface AbsentYearRepository extends CrudRepository<AbsentYear, Integer>{
	AbsentYear findByEmployeeAndYear(Employee emp, short year);
}
