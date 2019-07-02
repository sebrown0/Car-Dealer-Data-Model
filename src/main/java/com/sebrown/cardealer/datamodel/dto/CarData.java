/**
 * 
 */
package com.sebrown.cardealer.datamodel.dto;

import java.util.Calendar;

import lombok.Data;

/**
 * @author Steve Brown
 *
 *	DTO.
 */
@Data
public class CarData {

	private String modelVin;
	private String modelName;
	private String description;
	private String registration;
	private String colour;
	private String transmission;
	private String manufacturer;
	private double retailPrice;	
	private Calendar dateOfManufacture;	
	private boolean sunroof;
	private boolean alloyWheels;
	private boolean ac;
	private int horsepower;
	
	public CarData() {}
}
