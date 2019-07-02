/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Bean to record an employee's absenteeism.
 */
@Entity
@Component
@Getter @Setter
@NoArgsConstructor
@Table(name = "Employee_Absent")
public class EmployeeAttendance implements Serializable{
	
	private static final long serialVersionUID = 2255870196697105379L;

	@Id @Column(name = "emp_absent_id") 
	private int empAbsentId;
	
	@Column(name = "emp_id") 
	private int empId;
	
	@Column(name = "absent_start_date", nullable = false)
	private LocalDate absentStartDate;
	
	@Column(name = "absent_end_date", nullable = false)
	private LocalDate absentEndDate;
	
	@Column(name = "num_days", nullable = false)
	private long numDays;
	
	@Column(name = "reason", nullable = false)
	private String reason;
	
	@ManyToOne
	@JoinColumn(name="emp_absent_id", insertable = false, updatable = false)
    private AttendanceYear absentYear;	
}
