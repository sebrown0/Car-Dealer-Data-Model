/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;

/**
 * @author Steve Brown
 *
 */
@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long>{
	Manufacturer findManufacturerByName(String name); 	
}
