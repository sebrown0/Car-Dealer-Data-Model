/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Join table for an employee's role and seniority of that role within the department.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class RoleAndSeniority implements Serializable {

	private static final long serialVersionUID = -8505809679449708506L;

	@Id @Column(name = "ras_id")
	private int rasId;
	
	@OneToOne @JoinColumn(name = "seniority_id")
	private Seniority seniority;
		
	@ManyToOne @JoinColumn(name = "role_id")
	private Role role;
}
