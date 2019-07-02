
/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.util.List;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 *	Interface for a strategy to find a list of employees.
 */
public interface StrategyEmployeesFinder {
	List<Employee> findEmployees();
}
