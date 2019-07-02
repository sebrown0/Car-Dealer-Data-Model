/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.util.List;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 *  Finds an list of employees depending on the finding strategy injected.
 */
public class ContextEmployeesFinder {

	private final StrategyEmployeesFinder strategy;
		
	public ContextEmployeesFinder(StrategyEmployeesFinder strategy) {
		this.strategy = strategy;
	}

	public List<Employee> findEmployee() {
		return strategy.findEmployees();
	}
}
