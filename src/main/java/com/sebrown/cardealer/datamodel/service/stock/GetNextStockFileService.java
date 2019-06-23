/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebrown.cardealer.datamodel.dto.CarDto;
import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.StockFile;
import com.sebrown.cardealer.datamodel.model.stock.StockList;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockFileRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockListRepository;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;
import com.sebrown.cardealer.datamodel.util.GenericBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Steve Brown
 *
 * Find the next stock file name by getting the last used
 * name and incrementing the file id by one.
 * 
 * Example: car_stock_1.json becomes car_stock_1.json. 
 */
@Service
@Getter @Setter
@RequiredArgsConstructor
public class GetNextStockFileService {

	public static final String CAR_STOCK_PATH = ("src/main/resources/data/");
	// Injected with Lombok
	private final StockFileRepository stockFileRepo;
	private final StockStatusRepository stockStatusRepo;
	private final StockListRepository stockListRepo;
	private final ManufacturerRepository manRepo;
	
	private int fileNum; 
	private String nextFileName;
	
	/*
	 * Check is there's a new stock file. If there is prepend path to file.
	 */
	public String checkForNewStockFile() {
		nextFileName = getNextFileName();
		return (isThereANewStockFile(CAR_STOCK_PATH + nextFileName)) ? CAR_STOCK_PATH + nextFileName : null;
	}
	
	/*
	 * Get the name of the expected next stock file.
	 */
	public String getNextFileName() {
		fileNum = stockFileRepo.findLastStockFileNum() + 1;
		return String.format("car_stock_%d.json", fileNum);
	}
	
	/*
	 * Check to see if there's a new stock file.
	 */
	private boolean isThereANewStockFile(String filePath) {
		return (new File(filePath).exists()) ? true : false; 
	}
	
	/*
	 * Read a new stock file. If the file is read successfully then update associated tables.
	 */
	public void readStockFile(String stockFileName){
		ObjectMapper mapper = new ObjectMapper();
		List<CarDto> cars = null;
		try {
			cars = mapper.reader()
					.forType(new TypeReference<List<CarDto>>(){})
					.readValue(new File(stockFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkIfReadWasSuccessfull(cars);
	}

	/*
	 * See if the file was read successfully, i.e. cars contains data.
	 * If all good update the other stock tables.
	 */
	private void checkIfReadWasSuccessfull(List<CarDto> cars) {
		if (cars != null) {
			mapCarDtoToEntityAndPersist(cars);
			updateStockTablesAfterSuccessfulRead();
		}
	}
	
	/*
	 * Map the read Car DTO's to a Car entity. 
	 */
	private void mapCarDtoToEntityAndPersist(List<CarDto> carDtos) {
		ModelMapper modelMapper = new ModelMapper();
		StockStatus stockStatus = stockStatusRepo.findStockStatusByStatus("Awaiting Preparation");
		List<Car> newListOfStock = stockStatus.getCars(); 
				
		for (CarDto carDto : carDtos) {
			Car aNewCar = modelMapper.map(carDto, Car.class);
			aNewCar.setManufacturer(manRepo.findManufacturerByName(carDto.getManufacturer()));
			aNewCar.setStockStatus(stockStatus);
			newListOfStock.add(aNewCar);
		}
		stockStatusRepo.save(stockStatus);
	}
	
	/*
	 * Update the stock list and stock file tables.
	 */
	private void updateStockTablesAfterSuccessfulRead() {
		StockList updatedStockList = updatedStockList();
		updateStockFile(updatedStockList);
	}
	
	/*
	 * Update the stock list table tuple for "Awaiting Preparation" with the 
	 * date of the new stock file read.
	 */
	private StockList updatedStockList() {
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
				.with(StockFile::setFileId, fileNum)
				.with(StockFile::setFileName, nextFileName)
				.with(StockFile::setLastUpdate, stockList.getDateUpdated())
				.with(StockFile::setStockList, stockList)
				.build();
		stockFileRepo.save(newFile);
	}
}
