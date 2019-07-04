package com.sebrown.cardealer.datamodel.service.stock;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
/**
 * 
 * @author Steve Brown
 *
 */
@Service
public interface StockUpdateService {
	
	/**
	 * Update a stock list.
	 * 
	 * @param stockStatus: the status list with this stock status will be updated.
	 */
	void stockUpdate(StockStatus stockStatus);
}