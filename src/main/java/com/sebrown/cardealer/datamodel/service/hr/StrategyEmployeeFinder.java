/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 *	Interface for a strategy to find an employee.
 */
public interface StrategyEmployeeFinder {
	Employee findEmployee();
}
