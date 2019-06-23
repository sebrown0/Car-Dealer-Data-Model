/**
 * 
 */
package com.sebrown.cardealer.datamodel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepositoryHelper;
import com.sebrown.cardealer.datamodel.service.hr.EmployeeService;

/**
 * @author Steve Brown
 *
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepositoryHelper empRepoImpl;
	
//	@Autowired
//	EmployeeRepository empRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
    EmployeeService empService;
	
	@GetMapping("/employee")
	public List<String> getAllEmployees(){
		return empRepoImpl.getEmployeeNames();
	}
	
//	@GetMapping("/employee/{id}")
//	public Employee getEmployeeByID(@PathVariable(value="id") Integer empId){
//		return empRepo.findById(empId).get();
//	}
	
	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")		
	public String updateEmployee(@RequestBody EmployeeDto empDto){
		Employee e = empService.saveNewEmployee(empDto);
		return e.getFirstName() + " " + e.getLastName() + " " + e.getEmpId();
	}
	
}
