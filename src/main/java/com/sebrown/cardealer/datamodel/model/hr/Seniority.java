/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 *	Bean for Seniority table.
 *	This seniority of an employee within the company and the 
 *	renumeration for that seniority.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Seniority {

	@Id @Column(name = "seniority_id")
	private int seniorityId;
	
	@Column(nullable = false, name = "seniority")
	private String seniorityLevel;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "holiday_entitlement", nullable = false)
	private long holidayEntitlement;
			
	@Column(name = "salary_min", nullable = false)
	private BigDecimal salaryMin;
	
	@Column(name = "salary_max", nullable = false)
	private BigDecimal salaryMax;
			
}
