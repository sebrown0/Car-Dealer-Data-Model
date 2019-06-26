/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author Steve Brown
 *
 *	Bean for Department table.
 */
@Entity
@Data
@NamedQuery(name = "findDeptForDeptName", query = "SELECT d FROM Department d WHERE d.deptName=:d_name")
public class Department implements Serializable {

	private static final long serialVersionUID = 2987260559099517267L;

	@Id @Column(name = "dept_id")
	private int deptId;
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY)
	private Set<Employee> employees;
	
	@Column(name = "dept_name", nullable = false)
	private String deptName;
	
	@Column(nullable = true)
	private String description;
}
