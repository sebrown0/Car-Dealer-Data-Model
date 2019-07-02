/**
 * 
 */
package com.sebrown.cardealer.datamodel.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebrown.cardealer.datamodel.repository.stock.CarRepository;

/**
 * @author Steve Brown
 *
 */
@RestController
@RequestMapping("/api")
public class CarController {

	
	@Autowired
	CarRepository carRepo;
	
	//@Autowired
	
	
	
//	@GetMapping("/employee")
//	public List<String> getAllEmployees(){
//		return empRepoImpl.getEmployeeNames();
//	}
	
//	@GetMapping("/employee/{id}")
//	public Employee getEmployeeByID(@PathVariable(value="id") Integer empId){
//		return empRepo.findById(empId).get();
//	}
	
//	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")		
//	public String updateEmployee(@RequestBody EmployeeDto empDto){
//		Employee e = empService.saveNewEmployee(empDto);
//		return e.getFirstName() + " " + e.getLastName() + " " + e.getEmpId();
//	}
	
}
