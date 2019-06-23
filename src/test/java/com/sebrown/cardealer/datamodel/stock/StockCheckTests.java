package com.sebrown.cardealer.datamodel.stock;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.StockList;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.stock.CarRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockFileRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockListRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;
import com.sebrown.cardealer.datamodel.service.stock.GetNextStockFileService;

/*
 * Tests for Stock related functions.
 * For entities: StockFile, StockList and StockStatus.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StockCheckTests {
	
	@Autowired
	StockFileRepository stockFileRepo;

	@Autowired
	StockStatusRepository statusRepo;
	
	@Autowired
	StockListRepository listRepo;
	
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	GetNextStockFileService stockFileService;
	
	/*
	 * Tests for StockStatus.
	 */
	@Test
	public void findStockStatus() {
		StockStatus s = statusRepo.findStockStatusByStatus("Awaiting Preparation");
		assertEquals(3, s.getStatusId());
	}
	
	/*
	 * Tests for StockFile.
	 */
	@Test
	public void nextStockFileName() {
		String nextFile = stockFileService.getNextFileName();
		assertEquals("car_stock_1.json", nextFile);
	}
	
	@Test
	public void checkForNewStockFile() {
		String newFile = stockFileService.checkForNewStockFile();
		assertEquals(GetNextStockFileService.CAR_STOCK_PATH + stockFileService.getNextFileName(), newFile);
	}
		
	/*
	 * Tests for StockList.
	 */
	@Test 
	public void findStockListForStatus() {
		StockList stock = listRepo.findStockListForADefinedStatus(statusRepo.findStockStatusByStatus("Awaiting Preparation"));
		assertEquals("Awaiting Preparation", stock.getStockStatus().getStatus());
	}
	
	@Test 
	public void findCarsForADefinedStockStatus() {
		List<Car> carStock = listRepo.findCarsForADefinedStockStatus(statusRepo.findStockStatusByStatus("Awaiting Preparation"));
		for (Car car : carStock) {
			System.out.println(car);
		}
	}
	
	@Test 
	public void mapCarDtoToEntityAndPersist() {
		stockFileService.readStockFile(stockFileService.checkForNewStockFile());
		Car car = carRepo.findById("FD95219PLJ").orElse(null);
		assertEquals("black", car.getColour());
	}
	
}
