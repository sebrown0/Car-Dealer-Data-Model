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

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.AttendanceYear;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;
import com.sebrown.cardealer.datamodel.repository.hr.AttendanceYearRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAttendanceRepository;
import com.sebrown.cardealer.datamodel.repository.hr.RoleAndSeniorityRepository;
import com.sebrown.cardealer.datamodel.service.hr.ContextEmployeeAttendanceRecord;
import com.sebrown.cardealer.datamodel.service.hr.FindEmployeeSickLeave;
import com.sebrown.cardealer.datamodel.service.hr.RecordEmployeeAbsentService;
import com.sebrown.cardealer.datamodel.service.hr.UpdateEmployeeAnnualLeave;
import com.sebrown.cardealer.datamodel.service.hr.UpdateEmployeeSickLeave;
import com.sebrown.cardealer.datamodel.util.BusinessDaysCalculator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAbsentTests {
		
	@Autowired
	EmployeeAttendanceRepository empAbsentRepo;
	
	@Autowired
	RoleAndSeniorityRepository rasRepo;
		
	@Autowired
	AttendanceYearRepository attendanceYearRepo;
	
	@Autowired
	RecordEmployeeAbsentService empAbsentService;
						
	@Test
	public void findDaysAbsentForYearForEmployeeForReason() {
		AttendanceYear absentYear = attendanceYearRepo.findByEmployeeIdAndYear(2, (short)2020);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear, "Sick", 2);
		assertEquals(3, numDays);
	}

	@Test
	public void calaculateEmployeeAnnualLeaveRemaining() {
		AttendanceYear absentYear = attendanceYearRepo.findByEmployeeIdAndYear(1, (short)2019);
		long leave = rasRepo.findSeniority(4).getHolidayEntitlement();
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absentYear,  "Annual Leave", 1);
		assertEquals(24, leave - numDays);
	}
	
	@Test
	public void findEmployeeAbsentRecordsForYear() {
		AttendanceYear absentYear = attendanceYearRepo.findByEmployeeIdAndYear(1, (short)2019);
		List<EmployeeAttendance> empAbs = empAbsentRepo.findEmployeeAttendanceRecordForYear(absentYear.getYear(), 1);
		assertFalse(empAbs.isEmpty());
	}
	
	@Test
	public void calculateBusinessDays() {
		long days = BusinessDaysCalculator.calculateDaysBetweenDates(LocalDate.of(2019, 6, 1), LocalDate.of(2019, 6, 30));	
		assertEquals(20, days);
	}
	
	@Test
	public void doesEmployeeHaveAbsentRecordForYear() {
		
	}

	@Test
	public void employeeAnnualLeaveRequestAgreed() {
		EmployeeAttendanceData empAbsData = 
				new EmployeeAttendanceData(5, LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 18));
		EmployeeAttendance result = 
				empAbsentService.recordAbsentEvent(empAbsData, new UpdateEmployeeAnnualLeave(attendanceYearRepo, empAbsentRepo, rasRepo));
		assertTrue("Employee annual leave agreed", result != null);
	}
	
	@Test
	public void employeeAnnualLeaveRequestDenied() {
		EmployeeAttendanceData empAbsData = 
				new EmployeeAttendanceData(15, LocalDate.of(2019, 8, 1), LocalDate.of(2019, 9, 18));
		EmployeeAttendance result = 
				empAbsentService.recordAbsentEvent(empAbsData, new UpdateEmployeeAnnualLeave(attendanceYearRepo, empAbsentRepo, rasRepo));
		assertTrue("Employee annual leave denied", result == null);
	}
	
	@Test
	public void employeeSick() {		
		EmployeeAttendanceData empAbsData = 
				new EmployeeAttendanceData(7, LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 25));
		empAbsentService.recordAbsentEvent(empAbsData, new UpdateEmployeeSickLeave(empAbsentRepo));
		AttendanceYear absYear = attendanceYearRepo.findByEmployeeIdAndYear(7, (short)2019);
		long numDays = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absYear, "Sick", 7);
		assertEquals(18, numDays);
	}
	
	@Test
	public void numberOfDaysSickLeave() {
		long sickLeaveTaken = 
				new ContextEmployeeAttendanceRecord(new FindEmployeeSickLeave(2, (short)2020, attendanceYearRepo, empAbsentRepo))
				.getDaysAbsent();
		assertEquals(3, sickLeaveTaken);
	}
	
}
