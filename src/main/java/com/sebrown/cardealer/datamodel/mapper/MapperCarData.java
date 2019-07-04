/**
 * 
 */
package com.sebrown.cardealer.datamodel.mapper;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.dto.CarData;
import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepository;
import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepositoryImpl;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;
import com.sebrown.cardealer.datamodel.service.stock.FinderListByStrategy;
import com.sebrown.cardealer.datamodel.service.stock.StrategyStockList;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Steve Brown
 *
 */
@Service
@RequiredArgsConstructor
public class MapperCarData {
	
	private final StockStatusRepository statusRepo;
	private final ManufacturerRepository manRepo;
	
	/**
	 * Map Car Data DTO to StockStatus entity.
	 * 
	 * @param carData: list of car data that has been extracted from a stock file.
	 * @return the StockStatus entity.
	 */
	public StockStatus map(List<CarData> carData, StrategyStockList strategy) {
		ModelMapper modelMapper = new ModelMapper();
		StockStatus stockStatus = statusRepo.findStockStatusByStatus(strategy.getStrategy());
		List<Car> newListOfStock = new FinderListByStrategy<Car>().find(strategy);  
		
		Map<String, Manufacturer> manufacturers = mapManufacturers(manRepo);
		
		carData.stream().forEach(car -> {
			Car aNewCar = modelMapper.map(car, Car.class);
			aNewCar.setManufacturer(findManufacturer(manufacturers, car.getManufacturer()));
			aNewCar.setStockStatus(stockStatus);
			newListOfStock.add(aNewCar);
		});
		
		return stockStatus;
	}
	
	/**
	 * 
	 * @param manRepo: Manufacturer repository.
	 * @return map of manufacturers.
	 */
	private static Map<String, Manufacturer> mapManufacturers(ManufacturerRepository manRepo) {
		FindAllFromRepository<Manufacturer, Long> finder = new FindAllFromRepositoryImpl<Manufacturer, Long>(manRepo);
		MapperRepositoryToMap<String, Manufacturer, Long> mapper = new MapperManufacturerToMap();
		return mapper.map(finder);
	}
	
	/**
	 * 
	 * @param manufacturers: map of manufacturers.
	 * @param key: key to the map.
	 * @return a manufacturer entity. 
	 */
	private static Manufacturer findManufacturer(Map<?, Manufacturer> manufacturers, Object key) {
		return manufacturers.get(key);
	}
}
