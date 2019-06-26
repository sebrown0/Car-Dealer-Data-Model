/**
 * 
 */
package com.sebrown.cardealer.datamodel.model.stock;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Steve Brown
 *
 *	Bean for the 'Model' table.
 */
@Entity
@Table(name = "MODEL")
@Data
public class Car implements Serializable {

	private static final long serialVersionUID = -5915833929160559930L;

	@Id
	private String modelVin;
		
	@Column(nullable = false)
	private String modelName;
	
	@Column(nullable = false)
	private double retailPrice;
	
	@Column(nullable = false)
	private Calendar dateOfManufacture;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String registration;
	
	@Column(nullable = false)	
	private boolean sunroof;
	
	@Column(nullable = false)
	private boolean alloyWheels;
	
	@Column(nullable = false)
	private boolean ac;
	
	@Column(nullable = false)
	private String colour;
	
	@Column(nullable = false)
	private String transmission;
	
	@Column(nullable = false)
	private int horsepower;
	
	@ManyToOne @JoinColumn(name = "fk_manufacturer_id")
	private Manufacturer manufacturer;
		
	@ManyToOne @JoinColumn(name = "fk_stock_status_id")
	private StockStatus stockStatus;

}
