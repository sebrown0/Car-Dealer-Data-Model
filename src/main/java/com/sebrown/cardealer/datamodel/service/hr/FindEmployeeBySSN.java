/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;

import lombok.AllArgsConstructor;

/**
 * @author Steve Brown
 *
 * Find an employee for the employee's social security number.
 */
@AllArgsConstructor
public class FindEmployeeBySSN implements StrategyEmployeeFinder{

	private final String ssn;
	private final EmployeeFinderRepository empFinder;
	
	@Override
	public Employee findEmployee() {
		return empFinder.findEmployeeBySsn(ssn);
	}
}
