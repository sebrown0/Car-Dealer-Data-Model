/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.util.List;

import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeesFinderRepository;

import lombok.AllArgsConstructor;

/**
 * @author Steve Brown
 *
 * Find employees for a department ID.
 */
@AllArgsConstructor
public class FindEmployeesByDepartment implements StrategyEmployeesFinder{

	private final int deptId;
	private final EmployeesFinderRepository empFinder;

	@Override
	public List<Employee> findEmployees() {
		return empFinder.findEmployeesByDeptId(deptId);
	}
}
