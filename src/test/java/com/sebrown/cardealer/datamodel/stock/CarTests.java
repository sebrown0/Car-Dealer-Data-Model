package com.sebrown.cardealer.datamodel.stock;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;
import com.sebrown.cardealer.datamodel.repository.stock.CarRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;
import com.sebrown.cardealer.datamodel.util.GenericBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTests {
	
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	ManufacturerRepository manRepo;

	@Autowired
	StockStatusRepository statusRepo;
	
	@Test
	public void findManufacturer() {
		Manufacturer man = manRepo.findManufacturerByName("Ford");
		assertEquals(new Long(1), man.getId());
	}
	
	@Test
	public void saveNewCar() {
		Calendar dom = Calendar.getInstance();
		dom.set(2019, 05, 22);
		Manufacturer man = manRepo.findManufacturerByName("Ford");
		StockStatus stockStatus = statusRepo.findStockStatusByStatus("Awaiting Preparation");
		Car newCar = GenericBuilder.of(Car::new)
				.with(Car::setAc, true)
				.with(Car::setAlloyWheels, true)
				.with(Car::setColour, "Red")
				.with(Car::setDateOfManufacture, dom)
				.with(Car::setHorsepower, 5000)
				.with(Car::setManufacturer, man)
				.with(Car::setModelName, "Mustang")
				.with(Car::setModelVin, "FD95719MUS")
				.with(Car::setRegistration, "MUST 1")
				.with(Car::setRetailPrice, 65000.00)
				.with(Car::setStockStatus, stockStatus)
				.with(Car::setSunroof, true)
				.with(Car::setTransmission, "Auto")
				.build();
		carRepo.save(newCar);
	}
	
}
