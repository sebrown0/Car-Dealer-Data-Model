/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.stock;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.stock.Car;

/**
 * @author Steve Brown
 *
 */
public interface CarRepository extends CrudRepository<Car, String>{
	
}
