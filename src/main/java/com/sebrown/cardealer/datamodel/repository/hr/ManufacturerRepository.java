/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;

/**
 * @author Steve Brown
 *
 */
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long>{
	Manufacturer findManufacturerByName(String name); 	
}
