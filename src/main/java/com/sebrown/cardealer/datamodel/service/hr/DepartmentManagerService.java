/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;

/**
 * @author Steve Brown
 *
 *	Bean that finds the department manager for the given department id.
 */

@Service
public class DepartmentManagerService implements Serializable {

	private static final long serialVersionUID = 1049260444499517267L;

	@Autowired
	DepartmentRepository deptRepo;
	
	public DepartmentManagerService() {}
	
	public DepartmentManager getDepartmentManager(int deptId) {
		return deptRepo.findDeptManager(deptId);
	}
}
