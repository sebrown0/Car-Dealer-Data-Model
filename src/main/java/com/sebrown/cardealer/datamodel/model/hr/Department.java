/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Steve Brown
 *
 *	Bean for Department table.
 */
@Entity
@Getter @Setter 
@NoArgsConstructor
@ToString
@NamedQuery(name = "findDeptForDeptName", query = "SELECT d FROM Department d WHERE d.deptName=:d_name")
public class Department implements Serializable {

	private static final long serialVersionUID = 2987260559099517267L;

	@Id @Column(name = "dept_id")
	private int dept_id;
	
	@Column(name = "dept_name", nullable = false)
	private String deptName;
	
	@Column(nullable = true)
	private String description;
}
