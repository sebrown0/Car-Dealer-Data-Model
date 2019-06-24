/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Bean to record an employee's absenteeism.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class EmployeeAbsent {
	
	@Id @Column(name = "emp_absent_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empAbsentId;
	
	@Column(name = "absent_start_date", nullable = false)
	private LocalDate absentStartDate;
	
	@Column(name = "absent_end_date", nullable = false)
	private LocalDate absentEndDate;
	
	@Column(name = "num_days", nullable = false)
	private long numDays;
	
	@Column(name = "reason", nullable = false)
	private String reason;
	
	@ManyToOne 
	@JoinColumns({
		@JoinColumn(name="absent_year_id", referencedColumnName="absent_id"),
		@JoinColumn(name="absent_year_emp_id", referencedColumnName="emp_id")})
    private AbsentYear absentYear;
	
}
