/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.math.BigDecimal;

import com.sebrown.cardealer.datamodel.dto.EmployeeData;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.RoleAndSeniority;

/**
 * @author Steve Brown
 *
 */
public interface NewEmployeeService {
	
	Employee saveNewEmployee(EmployeeData newEmpDto);
	boolean employeeSalaryInTheCorrectRange(RoleAndSeniority ras, BigDecimal salary);
}
