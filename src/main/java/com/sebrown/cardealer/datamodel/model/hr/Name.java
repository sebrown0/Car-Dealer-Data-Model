/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Represents an employee's full name.
 */
@Embeddable
@Getter @Setter
@RequiredArgsConstructor
public class Name {

	@Column(name = "FIRST_NAME", insertable = false, updatable = false)
	private final String firstName;
	
	@Column(name = "LAST_NAME", insertable = false, updatable = false)
	private final String lastName;
	
	private String fullName;
		
	public String fullName() { return firstName + " " + lastName; }

}
