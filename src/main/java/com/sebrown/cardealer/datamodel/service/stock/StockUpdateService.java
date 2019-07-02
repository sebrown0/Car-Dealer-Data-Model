package com.sebrown.cardealer.datamodel.service.stock;

import com.sebrown.cardealer.datamodel.model.stock.StockStatus;
/**
 * 
 * @author Steve Brown
 *
 */
public interface StockUpdateService {
	
	/**
	 * Update a stock list.
	 * 
	 * @param stockStatus: the status list with this stock status will be updated.
	 */
	void stockUpdate(StockStatus stockStatus);
}