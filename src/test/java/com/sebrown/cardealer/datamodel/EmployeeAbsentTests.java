package com.sebrown.cardealer.datamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.AbsentYear;
import com.sebrown.cardealer.datamodel.model.Employee;
import com.sebrown.cardealer.datamodel.model.EmployeeAbsent;
import com.sebrown.cardealer.datamodel.repository.AbsentYearRepository;
import com.sebrown.cardealer.datamodel.repository.EmployeeAbsentRepository;
import com.sebrown.cardealer.datamodel.repository.EmployeeRepository;
import com.sebrown.cardealer.datamodel.repository.EmployeeRepositoryHelper;
import com.sebrown.cardealer.datamodel.repository.RoleAndSeniorityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAbsentTests {
		
	@Autowired
	EmployeeAbsentRepository empAbsentRepo;
	
	@Autowired
	RoleAndSeniorityRepository rasRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	AbsentYearRepository absYearRepo;
	
	@Autowired
	EmployeeRepositoryHelper empRepoHelper;
					
	@Test
	public void findDaysAbsentForYearForEmployeeForReason() {
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent((short)2019, 1, "Sick");
		assertEquals(1, numDays);
	}

	@Test
	public void calaculateEmployeeAnnualLeaveRemaining() {
		long leave = rasRepo.findSeniority(4).getHolidayEntitlement();
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent((short)2019, 1, "Annual Leave");
		assertEquals(22, leave - numDays);
	}

	@Test
	public void checkAnnualLeaveRequest() {
		Employee emp = empRepo.findById(1).orElse(null);
		AbsentYear absYear = absYearRepo.findByEmployeeAndYear(emp, (short)2019);
		assertTrue(empRepoHelper.checkAnnualLeaveRequest(absYear, 2, emp.getEmpId()));
	}
	
	@Test
	public void employeeAnnualLeaveRequestAgreed() {
		Employee emp = empRepo.findById(5).orElse(null);
		EmployeeAbsent result = empRepoHelper.recordEmployeeAbsent(
				emp, LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 25), "Annual Leave");
		assertTrue("Employee annual leave agreed", result != null);
	}
	
	@Test
	public void employeeAnnualLeaveRequestDenied() {
		Employee emp = empRepo.findById(5).orElse(null);
		EmployeeAbsent result = empRepoHelper.recordEmployeeAbsent(
				emp, LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 25), "Annual Leave");
		assertTrue("Employee annual leave denied", result == null);
	}
	
	@Test
	public void employeeSick() {
		Employee emp = empRepo.findById(5).orElse(null);
		empRepoHelper.recordEmployeeAbsent(
				emp, LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 25), "Sick");
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent((short)2019, 5, "Sick");
		assertEquals(18, numDays);
	}
}
