/**
 * 
 */
package com.sebrown.cardealer.datamodel.controller.hr;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebrown.cardealer.datamodel.dto.EmployeeData;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;
import com.sebrown.cardealer.datamodel.service.hr.ContextEmployeeFinder;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeeById;
import com.sebrown.cardealer.datamodel.service.hr.NewEmployeeService;

/**
 * @author Steve Brown
 *
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeFinderRepository empFinderRepo;
	
	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
    NewEmployeeService newEmpService;
	
	
//	@GetMapping("/employee")
//	public List<String> getAllEmployees(){
//		return empRepoImpl.getEmployeeNames();
//	}
	
	@GetMapping(path = "/id/{id}")
	public EmployeeData getEmployeeByID(@PathVariable(value="id") Integer empId){
		ContextEmployeeFinder empFinder = new ContextEmployeeFinder(new FindEmployeeById(empId, empFinderRepo));
		Employee e = empFinder.findEmployee();
		EmployeeData empData = new ModelMapper().map(e, EmployeeData.class);
		return empData;
	}	
	
	@PostMapping(path = "/new", consumes = "application/json", produces = "application/json")		
	public EmployeeData updateEmployee(@RequestBody EmployeeData empData){
		newEmpService.saveNewEmployee(empData);
		return empData;
	}
	
}
