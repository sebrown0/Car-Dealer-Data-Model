package com.sebrown.cardealer.datamodel.service.stock;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.repository.stock.StockStatusRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Steve Brown
 *
 * Get a list of car stock that has been "Ordered".
 */
@Service
@RequiredArgsConstructor
public class StrategyStockListOrder implements StrategyStockList {

	private static final String STOCK_STATUS = "Order";	
	private final StockStatusRepository stockRepo;
	
	@Override
	public List<Car> execute() {
		return stockRepo.findStockStatusByStatus(STOCK_STATUS).getCars();
	}

	@Override
	public String getStrategy() {
		return STOCK_STATUS;
	}
}
