/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.Role;
import com.sebrown.cardealer.datamodel.model.RoleAndSeniority;
import com.sebrown.cardealer.datamodel.model.Seniority;

/**
 * @author Steve Brown
 *
 */
public interface RoleAndSeniorityRepository extends CrudRepository<RoleAndSeniority, Integer>{
		
	@Query("SELECT ras FROM RoleAndSeniority ras WHERE ras.role.roleName=:role AND ras.seniority.seniorityLevel=:seniority")
	RoleAndSeniority findRoleAndSeniority(String role, String seniority);
	
	@Query("SELECT ras.seniority FROM RoleAndSeniority ras WHERE ras.rasId=:rasId")
	Seniority findSeniority(int rasId);

	@Query("SELECT ras.role FROM RoleAndSeniority ras WHERE ras.rasId=:rasId")
	Role findRole(int rasId);	
}
