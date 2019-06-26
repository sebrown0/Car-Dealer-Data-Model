package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author Steve Brown
 *
 * Employee entity.
 */
@NamedQuery(name = "findEmployeeForDept", 
	query = "select e from Employee e where e.empId = :eId and e.department.deptId = :dId")
@NamedQuery(name = "getEmpsNames", 
	query = "select e.firstName from Employee e")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "empRollCallByDeptartment", 
	    procedureName = "RollCall", 
	    resultClasses = { Employee.class }, 
	    parameters = { 
	        @StoredProcedureParameter(
	          name = "department_id", 
	          type = Integer.class, 
	          mode = ParameterMode.IN)
	}),
	@NamedStoredProcedureQuery(
		name="updateDepartmentManager",
		procedureName = "UpdateDepartmentManager",
		resultClasses = { Employee.class },
		parameters = {
			@StoredProcedureParameter(
					name = "new_manager_id",
					type = Integer.class,
					mode = ParameterMode.IN
			),
			@StoredProcedureParameter(
					name = "dept_name_in",
					type = String.class,
					mode = ParameterMode.IN
			) 
		})
})

@JsonIgnoreProperties(value = {"empId", "department"}, allowGetters = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 4659727704214359439L;

	@Id	@Column(name = "emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empId;
	
	@ManyToOne 	@JoinColumn(name = "dept_id")
	private Department department;
	
	@OneToOne @JoinColumn(name = "ras_Id")
	private RoleAndSeniority ras;
	
	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY)
	private Set<AbsentYear> absentYears;
	
	@Column(name = "manager_id", nullable = true)
	private Integer managerId = null;
	
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

