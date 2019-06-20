/**
 * 
 */
package com.sebrown.cardealer.datamodel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Bean for Model Enhancements table.
 */

@Getter @Setter
@NoArgsConstructor
public class ModelEnhancements {

	private String modelVin;
	private boolean sunroof;
	private boolean alloyWheels;
	private boolean ac;
}
