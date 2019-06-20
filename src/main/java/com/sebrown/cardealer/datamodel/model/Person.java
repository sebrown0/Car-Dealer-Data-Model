/**
 * 
 */
package com.sebrown.cardealer.datamodel.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Steve Brown
 *
 * Represents a person.
 */
@Embeddable
public class Person {

	public enum Salutation { MR, MRS, MISS, MS, DR, NONE };

	@Embedded
	private Name name;

	@Enumerated(EnumType.STRING)
	private Salutation salutation;

	public Person() {}
}
