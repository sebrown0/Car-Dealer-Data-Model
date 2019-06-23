package com.sebrown.cardealer.datamodel.service.hr;
import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.hr.Employee;

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
