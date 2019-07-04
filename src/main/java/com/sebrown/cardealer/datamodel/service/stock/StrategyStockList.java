package com.sebrown.cardealer.datamodel.service.stock;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.model.stock.Car;

/**
 * 
 * @author Steve Brown
 *
 *	Get the strategy for finding a specific type of stock list.
 */
@Service
public interface StrategyStockList extends StrategyListFinder<Car>{
	String getStrategy();
}
