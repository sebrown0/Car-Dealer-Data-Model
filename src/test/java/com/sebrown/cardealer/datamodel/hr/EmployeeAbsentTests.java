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
		AbsentYear absentYear = absYearRepo.findByEmployeeIdAndYear(2, (short)2020);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear, "Sick", 2);
		assertEquals(3, numDays);
	}

	@Test
	public void calaculateEmployeeAnnualLeaveRemaining() {
		AbsentYear absentYear = absYearRepo.findByEmployeeIdAndYear(1, (short)2019);
		long leave = rasRepo.findSeniority(4).getHolidayEntitlement();
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear,  "Annual Leave", 1);
		assertEquals(24, leave - numDays);
	}
	
	@Test
	public void findEmployeeAbsentRecordsForYear() {
		AbsentYear absentYear = absYearRepo.findByEmployeeIdAndYear(1, (short)2019);
		List<EmployeeAbsent> empAbs = empAbsentRepo.findEmployeeAbsentRecordsForYear(absentYear.getYear(), 1);
		assertFalse(empAbs.isEmpty());
	}

	@Test
	public void checkAnnualLeaveRequest() {
		AbsentYear absYear = absYearRepo.findByEmployeeIdAndYear(1, (short)2019);
		assertTrue(empRepoHelper.checkAnnualLeaveRequest(absYear, 2, 1));
	}
		
	@Test
	public void employeeAnnualLeaveRequestAgreed() {
		EmployeeAbsent result = empRepoHelper.recordEmployeeAbsent(
				5, LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 25), "Annual Leave");
		assertTrue("Employee annual leave agreed", result != null);
	}
	
	@Test
	public void employeeAnnualLeaveRequestDenied() {
		EmployeeAbsent result = empRepoHelper.recordEmployeeAbsent(
				5, LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 25), "Annual Leave");
		assertTrue("Employee annual leave denied", result == null);
	}
	
	@Test
	public void employeeSick() {
		empRepoHelper.recordEmployeeAbsent(
				7, LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 25), "Sick");
		AbsentYear absYear = absYearRepo.findByEmployeeIdAndYear(7, (short)2019);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absYear, "Sick", 7);
		assertEquals(18, numDays);
	}
}
