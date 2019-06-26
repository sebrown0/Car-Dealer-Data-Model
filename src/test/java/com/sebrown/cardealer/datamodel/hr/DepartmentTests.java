package com.sebrown.cardealer.datamodel.hr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.hr.Department;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository.DepartmentManager;
import com.sebrown.cardealer.datamodel.service.hr.DepartmentManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {

	@Autowired
	DepartmentRepository deptRepo;
	
	@Autowired
	DepartmentManagerService dMan;
			
	@Test
	public void findDepartmentManager() {
		DepartmentManager manager = deptRepo.findDeptManager(1);
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
}
