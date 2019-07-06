/**
 * 
 */
package com.sebrown.cardealer.datamodel.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebrown.cardealer.datamodel.repository.stock.CarRepository;

/**
 * @author Steve Brown
 *
 */
@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	CarRepository carRepo;	
}
