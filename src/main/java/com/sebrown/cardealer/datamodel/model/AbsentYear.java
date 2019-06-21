/**
 * 
 */
package com.sebrown.cardealer.datamodel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Steve Brown
 *
 * Bean for joining a year with employee's absent record. 
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class AbsentYear implements Serializable {

	private static final long serialVersionUID = -4971738461596965212L;

	@Id @Column(name = "absent_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int absentId;
	
	@ManyToOne @JoinColumn(name = "emp_id")
	private Employee employee;
	
	@Column(name = "year", nullable = false)
	private short year;
}
