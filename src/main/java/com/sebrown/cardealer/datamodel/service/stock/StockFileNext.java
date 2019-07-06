/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.io.File;

import org.springframework.stereotype.Component;

import com.sebrown.cardealer.datamodel.repository.stock.StockFileRepository;
import com.sebrown.cardealer.datamodel.service.ServiceConfiguration;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Checks if there is a new stock file to be read.
 */
@Component
@RequiredArgsConstructor
public class StockFileNext implements StockFileFinder, StockFileInfo{

	private final StockFileRepository stockFileRepo;
		
	private int nextFileNum;
	private String nextFileName;
	private String nextFilePath;
	private boolean fileFound = false;
	
	@Override
	public StockFileNext checkForNewFile() {
		if(aNewStockFileHasBeenFound()) {
			nextFilePath = ServiceConfiguration.CAR_STOCK_PATH + nextFileName;
			fileFound = true;
		}
		return this;
	}
	
	private boolean aNewStockFileHasBeenFound() {
		nextFileName = nextFileName();
		return (isThereANewStockFile(ServiceConfiguration.CAR_STOCK_PATH + nextFileName)) ? true : false;
	}
	
	private String nextFileName() {
		nextFileNum = stockFileRepo.findLastStockFileNum() + 1;
		return String.format("car_stock_%d.json", nextFileNum);
	}
	
	private boolean isThereANewStockFile(String filePath) {
		return (new File(filePath).exists()) ? true : false; 
	}

	@Override
	public String getFileName() {
		return nextFileName;
	}

	@Override
	public String getFilePath() {
		return nextFilePath;
	}

	@Override
	public int getFileNum() {
		return nextFileNum;
	}

	@Override
	public boolean stockFileFound() {
		return fileFound;
	}
}
