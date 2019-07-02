/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.hr.Role;
import com.sebrown.cardealer.datamodel.model.hr.RoleAndSeniority;
import com.sebrown.cardealer.datamodel.model.hr.Seniority;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface RoleAndSeniorityRepository extends CrudRepository<RoleAndSeniority, Integer>{
		
	@Query("SELECT ras FROM RoleAndSeniority ras WHERE ras.role.roleName=:role AND ras.seniority.seniorityLevel=:seniority")
	RoleAndSeniority findRoleAndSeniority(String role, String seniority);
	
	@Query("SELECT ras.seniority FROM RoleAndSeniority ras WHERE ras.rasId=:rasId")
	Seniority findSeniority(int rasId);

	@Query("SELECT ras.role FROM RoleAndSeniority ras WHERE ras.rasId=:rasId")
	Role findRole(int rasId);	
}
