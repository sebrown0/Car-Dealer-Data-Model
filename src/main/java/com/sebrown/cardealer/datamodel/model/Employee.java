package com.sebrown.cardealer.datamodel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Steve Brown
 *
 * Employee entity.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"empId", "department"}, 
	allowGetters = true)
@NamedQuery(name = "findEmployeeForDept", 
	query = "select e from Employee e where e.empId = :eId and e.department.dept_id = :dId")
@NamedQuery(name = "getEmpsNames", 
	query = "select e.firstName from Employee e")
@Getter @Setter 
@NoArgsConstructor
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 4659727704214359439L;

	@Id	
	@Column(name = "emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empId;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;
	
	@OneToOne @JoinColumn(name = "ras_Id")
	private RoleAndSeniority ras;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
		
	@Column(name = "salary", nullable = false)
	private BigDecimal salary;
	
	@Column(name = "ssn",nullable = false)
	private String ssn;
	
	@Column(name = "annual_leave", nullable = false)
	private long annualLeave;
		
	@Temporal(TemporalType.DATE)
	@Column(name = "dob", nullable = false)
	private Calendar dob;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date", nullable = false)
	private Calendar hireDate;
		
}

