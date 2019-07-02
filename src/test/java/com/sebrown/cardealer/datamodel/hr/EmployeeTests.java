package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.dto.EmployeeData;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.Name;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeesFinderRepository;
import com.sebrown.cardealer.datamodel.service.hr.ContextEmployeeFinder;
import com.sebrown.cardealer.datamodel.service.hr.ContextEmployeesFinder;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeeById;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeeByName;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeeBySSN;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeesByDepartment;
import com.sebrown.cardealer.datamodel.service.hr.NewEmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTests {

	@Autowired
	EmployeeFinderRepository empRepo;
	
	@Autowired
	NewEmployeeService newEmpService;
		
	@Autowired
	EmployeeFinderRepository empFinderRepo;
	
	@Autowired
	EmployeesFinderRepository empsFinderRepo;
	
	@Autowired
	EntityManager em;
			
	@Test
	public void findEmpById() {
		ContextEmployeeFinder empFinder = new ContextEmployeeFinder(new FindEmployeeById(5, empFinderRepo));
		Employee emp = empFinder.findEmployee();
		assertTrue(emp != null);
	}
	
	@Test
	public void findEmpByName() {
		ContextEmployeeFinder empFinder = new ContextEmployeeFinder(new FindEmployeeByName("Bart", "Simpson", empFinderRepo));
		Employee emp = empFinder.findEmployee();
		assertEquals("1213-BS-1996", emp.getSsn());
	}
		
	@Test
	public void findEmployeeBySsn() {
		ContextEmployeeFinder empFinder = new ContextEmployeeFinder(new FindEmployeeBySSN("1213-BS-1996", empFinderRepo));
		Employee emp = empFinder.findEmployee();
		assertEquals("Bart", emp.getFirstName());
	}
	
	@Test
	public void findEmployeesForDepartment() {
		ContextEmployeesFinder empsFinder = new ContextEmployeesFinder(new FindEmployeesByDepartment(1, empsFinderRepo));
		List <Employee> departmentEmps = empsFinder.findEmployee();
		assertTrue(departmentEmps.stream().count() > 0);
	}
	
	@Test
	public void saveNewEmployee() {		
		Name empName = new Name("Employee", "Test-1");
		Calendar empDob = Calendar.getInstance();
		empDob.set(1991, 10, 10);
		
		EmployeeData newEmp = EmployeeData.builder()
				.deptName("Sales")
				.firstName(empName.getFirstName())
				.lastName(empName.getLastName())
				.roleName("Salesperson")
				.salary(new BigDecimal("20000"))
				.seniority("Associate")
				.hireDate(Calendar.getInstance())
				.dob(empDob)
				.ssn("1861-ET1-1991")
				.build();
		
		Employee result = newEmpService.saveNewEmployee(newEmp);
		assertTrue("Failed to persist new employee", result != null);
	}
			
	@Test
	public void findAnEmployeesSalary() {
		Employee emp = empRepo.findById(3).orElse(null);
		assertTrue("Incorrect salary.", emp.getSalary().compareTo(new BigDecimal("21222.00")) == 0);
	}
	
	@Test
	public void checkEmployeesSalary() {
		Employee emp = empRepo.findById(3).orElse(null);
		assertTrue("Employee's salary is not in the correct range.", 
				newEmpService.employeeSalaryInTheCorrectRange(emp.getRas(), emp.getSalary()));
	}
		
	@Test
	@SuppressWarnings("unchecked")
	public void rollCallForADepartment() {
		StoredProcedureQuery rollCall = em.createNamedStoredProcedureQuery("empRollCallByDeptartment");
        rollCall.setParameter("department_id", 3);
        List<Employee> emps = rollCall.getResultList();
		assertEquals("Sally Field", emps.get(0).getFirstName() + " " + emps.get(0).getLastName());
	}
}
