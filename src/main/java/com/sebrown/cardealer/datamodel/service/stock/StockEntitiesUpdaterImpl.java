/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.model.stock.StockFile;
import com.sebrown.cardealer.datamodel.model.stock.StockList;
import com.sebrown.cardealer.datamodel.repository.stock.StockFileRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockListRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;
import com.sebrown.cardealer.datamodel.util.GenericBuilder;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 */
@Service
@RequiredArgsConstructor
public class StockEntitiesUpdaterImpl implements StockEntitiesUpdater{

	private final StockFileRepository stockFileRepo;
	private final StockStatusRepository stockStatusRepo;
	private final StockListRepository stockListRepo;
	private final StockFileInfo nextStockFile;

	/*
	 * Update the stock list and stock file tables.
	 */
	@Override
	public  void updateStockTablesAfterSuccessfulRead() {
		StockList updatedStockList = updateStockList();
		updateStockFile(updatedStockList);
	}
	
	/*
	 * Update the stock list table tuple for "Awaiting Preparation" with the 
	 * date of the new stock file read.
	 */
	private StockList updateStockList() {
		StockList stockList = stockListRepo
				.findStockListForADefinedStatus(stockStatusRepo.findStockStatusByStatus("Awaiting Preparation"));
		stockList.setDateUpdated(Calendar.getInstance());
		return stockListRepo.save(stockList);		
	}
	
	/*
	 * Update the stock file table with the file just read.
	 */
	private void updateStockFile(StockList stockList) {
		StockFile newFile = GenericBuilder.of(StockFile::new)
				.with(StockFile::setFileId, nextStockFile.getFileNum())
				.with(StockFile::setFileName, nextStockFile.getFileName())
				.with(StockFile::setLastUpdate, stockList.getDateUpdated())
				.with(StockFile::setStockList, stockList)
				.build();
		stockFileRepo.save(newFile);
	}
}
