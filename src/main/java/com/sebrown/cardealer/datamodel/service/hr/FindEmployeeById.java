/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Find an employee for the employee's ID.
 */
@RequiredArgsConstructor
public class FindEmployeeById implements StrategyEmployeeFinder{

	private final int id;
	private final EmployeeFinderRepository empFinder;

	@Override 
	public Employee findEmployee() {
		return empFinder.findById(id).orElse(null);
	}
}
