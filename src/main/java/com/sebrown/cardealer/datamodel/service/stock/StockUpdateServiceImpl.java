/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebrown.cardealer.datamodel.dto.CarData;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockFileRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockListRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * If there is a new stock file read it and persist it to the 
 * the data source. 
 * 
 * Then update the stock list table with the date of the successful read.
 * Update the stock file table with the next file to read.
 * 
 * For Example: car_stock_1.json becomes car_stock_2.json.
 *  
 */
@Service
@Getter @Setter
@RequiredArgsConstructor
@Transactional
public class StockUpdateServiceImpl implements StockUpdateService {

	private final StockFileRepository stockFileRepo;
	private final StockStatusRepository stockStatusRepo;
	private final StockListRepository stockListRepo;
	private final ManufacturerRepository manRepo;
	
	private StockEntitiesUpdater stockEntitiesUpdater;
	private StockFileInfo nextStockFile;
	private List<CarData> carData;
	
	@Override
	public void stockUpdate(StockStatus stockStatus) {
		if(thereIsAFileToRead() && fileReadOk()) {
			if(persistedStockOk()) {
				updateAssociatedStockEntities();
			}
		}
	}
		
	private boolean thereIsAFileToRead() {
		StockFileFinder findNextFile = new StockFileNext(stockFileRepo);
		nextStockFile = findNextFile.checkForNewFile();
		return (nextStockFile != null) ? true : false;
	}
	
	private boolean fileReadOk() {
		carData = StockFileReader.read(nextStockFile);
		return (carData.isEmpty()) ? false : true;
	}
	
	private boolean persistedStockOk() {
		StockStatus s = stockStatusRepo.save(CarDataMapper.map(stockStatusRepo, manRepo, carData));
		return (s == null) ? false : true;
	}
	
	private void updateAssociatedStockEntities() {
		stockEntitiesUpdater = new StockEntitiesUpdaterImpl(stockFileRepo, stockStatusRepo, stockListRepo, nextStockFile);
		stockEntitiesUpdater.updateStockTablesAfterSuccessfulRead();	
	}
}
