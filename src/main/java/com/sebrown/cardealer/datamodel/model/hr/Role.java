/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;

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
public class Role implements Serializable {

	private static final long serialVersionUID = 8041968088843226054L;

	@Id @Column(name = "role_id")
	private int roleId;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "is_commision_payable", nullable = false)
	private boolean isCommisionPayable;
}
