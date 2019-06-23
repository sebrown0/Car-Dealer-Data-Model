/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Represents a person's address.
 */
@Embeddable
@Getter @Setter 
@NoArgsConstructor
public class Address {

	private String addr1;
	private String addr2;
	private String zip;
	private String country;
}
