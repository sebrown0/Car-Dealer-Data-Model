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
 * Find an employee for the employee's name.
 */
@AllArgsConstructor
public class FindEmployeeByName implements StrategyEmployeeFinder{

	private final String firstName;
	private final String lastName;
	private final EmployeeFinderRepository empFinder;
	
	@Override
	public Employee findEmployee() {
		return empFinder.findByName(firstName, lastName);
	}
}
