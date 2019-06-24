package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.hr.AbsentYear;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAbsent;
import com.sebrown.cardealer.datamodel.repository.hr.AbsentYearRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAbsentRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepositoryHelper;
import com.sebrown.cardealer.datamodel.repository.hr.RoleAndSeniorityRepository;

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
		Employee emp = empRepo.findById(2).orElse(null);
		AbsentYear absentYear = absYearRepo.findByEmployeeAndYear(emp, (short)2020);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear, "Sick");
		assertEquals(3, numDays);
	}

	@Test
	public void calaculateEmployeeAnnualLeaveRemaining() {
		Employee emp = empRepo.findById(1).orElse(null);
		AbsentYear absentYear = absYearRepo.findByEmployeeAndYear(emp, (short)2019);
		long leave = rasRepo.findSeniority(4).getHolidayEntitlement();
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear,  "Annual Leave");
		assertEquals(24, leave - numDays);
	}
	
	@Test
	public void findEmployeeAbsentRecordsForYear() {
		Employee emp = empRepo.findById(1).orElse(null);
		AbsentYear absentYear = absYearRepo.findByEmployeeAndYear(emp, (short)2019);
		List<EmployeeAbsent> empAbs = empAbsentRepo.findEmployeeAbsentRecordsForYear(absentYear.getYear(), emp.getEmpId());
		assertFalse(empAbs.isEmpty());
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
		AbsentYear absYear = absYearRepo.findByEmployeeAndYear(emp, (short)2019);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absYear, "Sick");
		assertEquals(18, numDays);
	}
}
