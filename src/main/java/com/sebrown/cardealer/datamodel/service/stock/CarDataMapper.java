/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.dto.CarData;
import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;

/**
 * @author Steve Brown
 *
 */
@Service
public class CarDataMapper {

	public static StockStatus map(StockStatusRepository stockStatusRepo, ManufacturerRepository manRepo, List<CarData> carData) {
		ModelMapper modelMapper = new ModelMapper();
		StockStatus stockStatus = stockStatusRepo.findStockStatusByStatus("Awaiting Preparation");
		List<Car> newListOfStock = stockStatus.getCars(); 
				
		for (CarData car : carData) {
			Car aNewCar = modelMapper.map(car, Car.class);
			aNewCar.setManufacturer(manRepo.findManufacturerByName(car.getManufacturer()));
			aNewCar.setStockStatus(stockStatus);
			newListOfStock.add(aNewCar);
		}
		return stockStatus;
	}
}
