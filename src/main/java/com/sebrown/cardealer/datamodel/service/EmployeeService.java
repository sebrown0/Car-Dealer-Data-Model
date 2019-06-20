package com.sebrown.cardealer.datamodel.service;
import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.Employee;

/**
 * 
 */

/**
 * @author Steve Brown
 *
 */
public interface EmployeeService {

	Employee saveNewEmployee(EmployeeDto empDto);
}
