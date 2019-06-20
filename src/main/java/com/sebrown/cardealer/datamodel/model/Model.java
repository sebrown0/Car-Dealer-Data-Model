/**
 * 
 */
package com.sebrown.cardealer.datamodel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 *	Bean for the 'Model' table.
 */
@Entity
@Getter @Setter 
@NoArgsConstructor
public class Model {

	@Id
	private String modelVin;
	
	@Column(nullable = false, name = "manufacturer_id")
	private String manId;
	
	@Column(nullable = false)
	private String modelName;
	
	@Column(nullable = false)
	private double retailPrice;
	
	@Column(nullable = false)
	private Date dateOfManufacture;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String registration;
	
	@ManyToOne
	private Manufacturer manufacturer;
		
	public Model(String vin, String name, double retailPrice, Date manufactureDate) {
		super();
		this.modelVin = vin;
		this.modelName = name;
		this.retailPrice = retailPrice;
		this.dateOfManufacture = manufactureDate;
	}
}
