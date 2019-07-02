/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import com.sebrown.cardealer.datamodel.model.hr.Employee;

/**
 * @author Steve Brown
 *
 *  Finds an employee depending on the finding strategy injected.
 */
public class ContextEmployeeFinder {

	private final StrategyEmployeeFinder strategy;
		
	public ContextEmployeeFinder(StrategyEmployeeFinder strategy) {
		this.strategy = strategy;
	}

	public Employee findEmployee() {
		return strategy.findEmployee();
	}
}
