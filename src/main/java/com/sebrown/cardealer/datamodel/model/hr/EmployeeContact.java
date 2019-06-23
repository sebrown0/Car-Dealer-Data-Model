/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.hr;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * An employee's emergency contact.
 */
@Entity
@Getter @Setter 
@NoArgsConstructor
public class EmployeeContact {

	public enum PhoneType { HOME, WORK, MOB };
	
	@Id
	private int empId;
	
	@Embedded
	private Person contact;
	
	@Embedded
	private Address address;
	
	@ElementCollection
	@CollectionTable(name = "CONTACT_PHONE_NUMBERS", joinColumns=@JoinColumn(name="EMP_CONTACT_ID"))
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name="phone_type")
	@Column(name="PHONE_NUM")
	private Map<PhoneType, String> phoneNumbers;
	
}
