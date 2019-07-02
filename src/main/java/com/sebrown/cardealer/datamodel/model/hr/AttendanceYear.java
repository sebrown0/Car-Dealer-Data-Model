/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Steve Brown
 *
 * Bean for joining a year with employee's absent record. 
 */
@Entity
@Data
@Table(name = "Absent_Year")
public class AttendanceYear implements Serializable {

	private static final long serialVersionUID = -4971738461596965212L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int absentId;
		
	@ManyToOne @JoinColumn(name="fk_emp_id", referencedColumnName="emp_id")
	private Employee employee;
	
	@OneToMany(mappedBy="absentYear", fetch=FetchType.EAGER, cascade=CascadeType.ALL)  
	private Set<EmployeeAttendance> employeeAbsents;
	
	@Column(name = "year", nullable = false)
	private short year;
}
