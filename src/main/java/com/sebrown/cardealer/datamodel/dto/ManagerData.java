package com.sebrown.cardealer.datamodel.dto;

import com.sebrown.cardealer.datamodel.service.hr.DepartmentManager;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Steve Brown
 *
 */
@Getter @Setter
public class ManagerData {
	private Integer managerId;
	private String firstName;
	private String lastName;
	private String departmentName;
	
	public ManagerData(DepartmentManager manager) {
		this.managerId = checkManagerId(manager);
	}
	
	private Integer checkManagerId(DepartmentManager manager) {
		return (manager != null && manager.getManagerId() > 0) ? manager.getManagerId() : null; 
	}
	// Other checks as necessary.
}

