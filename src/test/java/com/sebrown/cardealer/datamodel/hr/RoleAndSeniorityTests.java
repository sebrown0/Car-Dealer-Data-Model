package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.Role;
import com.sebrown.cardealer.datamodel.model.hr.RoleAndSeniority;
import com.sebrown.cardealer.datamodel.model.hr.Seniority;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeRepository;
import com.sebrown.cardealer.datamodel.repository.hr.RoleAndSeniorityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleAndSeniorityTests {
	
	@Autowired
	private RoleAndSeniorityRepository roleRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
			
	@Test
	public void findRoleAndSeniorityForARoleAndSeniority() {
		RoleAndSeniority ras = roleRepo.findRoleAndSeniority("HR", "Manager");
		assertEquals(4, ras.getRasId());
	}
	
	@Test
	public void findAnnualLeaveForEmpsRasId() {
		Seniority s = roleRepo.findSeniority(4);
		assertEquals(25, s.getHolidayEntitlement());
	}
	
	@Test
	public void findRole() {
		Role r = roleRepo.findRole(15);
		assertEquals("Seceratary", r.getRoleName());
	}

	@Test
	public void checkEmpsSalaryIsInCorrectSalaryRange() {
		Employee e = empRepo.findById(3).orElse(null);
		Seniority s = roleRepo.findSeniority(e.getRas().getRasId());
		assertTrue("Employee's salary is lower then the min salary allowed", e.getSalary().compareTo(s.getSalaryMin()) >= 0);
		assertTrue("Employee's salary is higher then the max salary allowed", e.getSalary().compareTo(s.getSalaryMax()) <= 0);
	}
}
