/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 *	Bean for Role table.
 *	This is the role of an employee within the company.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Role {

	@Id @Column(name = "role_id")
	private int roleId;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "is_commision_payable", nullable = false)
	private boolean isCommisionPayable;
}
