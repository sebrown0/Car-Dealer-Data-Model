/**
 * 
 */
package com.sebrown.cardealer.datamodel.controller.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebrown.cardealer.datamodel.dto.EmployeeData;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.service.hr.NewEmployeeService;

/**
 * @author Steve Brown
 *
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

//	@Autowired
//	EmployeeRepository empRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
    NewEmployeeService newEmpService;
	
//	@GetMapping("/employee")
//	public List<String> getAllEmployees(){
//		return empRepoImpl.getEmployeeNames();
//	}
	
//	@GetMapping("/employee/{id}")
//	public Employee getEmployeeByID(@PathVariable(value="id") Integer empId){
//		return empRepo.findById(empId).get();
//	}
	
	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")		
	public String updateEmployee(@RequestBody EmployeeData empDto){
		Employee e = newEmpService.saveNewEmployee(empDto);
		return e.getFirstName() + " " + e.getLastName() + " " + e.getEmpId();
	}
	
}
