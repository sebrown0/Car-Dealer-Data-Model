package com.sebrown.cardealer.datamodel.stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.sebrown.cardealer.datamodel.service.stock.FinderListByStrategy;
import com.sebrown.cardealer.datamodel.service.stock.StockFileFinder;
import com.sebrown.cardealer.datamodel.service.stock.StockFileNext;
import com.sebrown.cardealer.datamodel.service.stock.StockUpdateService;
import com.sebrown.cardealer.datamodel.service.stock.StrategyStockListAwaitingPrep;
import com.sebrown.cardealer.datamodel.service.stock.StrategyStockListOrder;

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
	StockUpdateService updateStockService;
		
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
	public void checkForNewStockFile() {
		StockFileFinder findNextFile = new StockFileNext(stockFileRepo);
		StockFileNext nextStockFile = findNextFile.checkForNewFile();
		assertNotNull(nextStockFile.getFileNum());
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
	public void findCarsForStockStatusAwaitingPrep() {
		List<Car> carStock = new FinderListByStrategy<Car>().find(new StrategyStockListAwaitingPrep(statusRepo));
		assertEquals("FD12345FCS", carStock.get(0).getModelVin());
	}
	
	@Test 
	public void findCarsForStockStatusOrder() {
		List<Car> carStock = new FinderListByStrategy<Car>().find(new StrategyStockListOrder(statusRepo));
		assertTrue(carStock.isEmpty());
	}
	
	@Test 
	public void stockUpdate() {
		updateStockService.stockUpdate(statusRepo.findStockStatusByStatus("Awaiting Preparation"));
		Car car = carRepo.findById("FD12345FCS").orElse(null);
		assertEquals("black", car.getColour());
	}
}
