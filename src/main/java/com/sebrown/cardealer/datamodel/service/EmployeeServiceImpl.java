/**
 * 
 */
package com.sebrown.cardealer.datamodel.service;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.Department;
import com.sebrown.cardealer.datamodel.model.Employee;
import com.sebrown.cardealer.datamodel.repository.DepartmentRepository;
import com.sebrown.cardealer.datamodel.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Service implementation for the employee entity.
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository empRepo;
	private final DepartmentRepository deptRepo;

	@Override
	public Employee saveNewEmployee(EmployeeDto empDto) {
		Employee newEmp = buildEmp(empDto);
		empRepo.save(newEmp);
		
		return newEmp;
		// Now do the resy of the updates
	}

	private Employee buildEmp(EmployeeDto empDto) {
		Department d = deptRepo.findByDeptName(empDto.getDeptName());
		Employee newEmp = new Employee();
		newEmp.setFirstName(empDto.getFirstName());
		newEmp.setLastName(empDto.getLastName());
		newEmp.setDepartment(d);		
		return newEmp;
	}
	
}
