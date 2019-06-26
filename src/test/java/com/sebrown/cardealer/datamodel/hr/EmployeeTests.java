package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.Name;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepositoryHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeTests {

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	EmployeeRepositoryHelper empRepoHelper;
	
	@Autowired
	EntityManager em;
			
	@Test
	public void findEmpById() {
		Employee emp = empRepo.findById(5).orElse(null);
		assertTrue(emp != null);
	}
	
	@Test
	public void findEmployeesForDepartment() {
		List <Employee> departmentEmps = empRepo.findEmployeesForDeptId(1);
		assertTrue(departmentEmps.stream().count() > 0);
	}
	
	@Test
	public void findEmployeeBySsn() {
		Employee e = empRepo.findEmployeeBySsn("1213-BS-1996");
		assertEquals("Bart", e.getFirstName());
	}
	
	@Test
	public void saveNewEmployee() {		
		Name empName = new Name("Employee", "Test-1");
		Calendar empDob = Calendar.getInstance();
		empDob.set(1991, 10, 10);
		
		EmployeeDto newEmp = EmployeeDto.builder()
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
		
		Employee result = empRepoHelper.saveNewEmployee(newEmp);
		assertTrue("Failed to persist new employee", result != null);
	}
			
	@Test
	public void findAnEmployeesSalary() {
		Employee emp = empRepo.findById(1).orElse(null);
		assertTrue("Incorrect salary.", emp.getSalary().compareTo(new BigDecimal("20000.00")) == 0);
	}
	
	@Test
	public void checkEmployeesSalary() {
		Employee emp = empRepo.findById(1).orElse(null);
		assertTrue("Employee's salary is not in the correct range.", 
				empRepoHelper.employeeSalaryInTheCorrectRange(emp.getRas(), emp.getSalary()));
	}
	
	@Test
	public void calculateBusinessDays() {
		long days = empRepoHelper
				.calculateNumberOfDaysAbsentInclusive(LocalDate.of(2018, 6, 18), LocalDate.of(2018, 6, 24));
		assertEquals(6, days);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void rollCall() {
		StoredProcedureQuery rollCall = em.createNamedStoredProcedureQuery("empRollCallByDeptartment");
        rollCall.setParameter("department_id", 1);
        List<Employee> emps = rollCall.getResultList();
		assertEquals("Sid James", emps.get(0).getFirstName() + " " + emps.get(0).getLastName());
	}
}
