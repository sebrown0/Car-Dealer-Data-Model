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

import lombok.Data;

/**
 * @author Steve Brown
 *
 *	Bean for DepartmentManager table.
 */
@Entity
@Data
public class DepartmentManager implements Serializable {

	private static final long serialVersionUID = 2987260559069517867L;

	@Id @Column(name = "manager_id")
	private int managerId;
	
	@ManyToOne @JoinColumn(name="dept_id", referencedColumnName="dept_id")
	private Department department;
	
	@ManyToOne @JoinColumn(name="manager_id", referencedColumnName="emp_id")
	private Employee employee;
	
	@Column(name = "dept_name", nullable = false)
	private String deptName;
}
