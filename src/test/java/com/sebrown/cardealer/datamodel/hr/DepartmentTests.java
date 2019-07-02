package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.dto.ManagerData;
import com.sebrown.cardealer.datamodel.model.hr.Department;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.service.hr.DepartmentManager;
import com.sebrown.cardealer.datamodel.service.hr.DepartmentManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {

	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
	DepartmentManagerService deptManagerService;
			
	@Test
	public void findDepartmentManager() {
		DepartmentManager manager = deptManagerService.getDepartmentManager(1);
		assertEquals("HR", manager.getDepartmentName());
	}
	
	@Test 
	public void setDepartmentManager() {
		Department d = deptRepo.findById(3).orElse(null);
		DepartmentManager manager = deptRepo.findDeptManager(3);
		d.setDepartmentManager(manager);
		assertEquals("IT", d.getManager().getDepartmentName());
		assertEquals("Lisa", d.getManager().getFirstName());
		assertEquals("Simpson", d.getManager().getLastName());
	}
	
	@Test
	public void managerDTO() {
		ManagerData manager = new ManagerData(deptManagerService.getDepartmentManager(10));
		assertTrue(manager.getManagerId() == null);
	}
}
