package com.sebrown.cardealer.datamodel.service.stock;

import com.sebrown.cardealer.datamodel.model.stock.StockStatus;

public interface UpdateStockService {
	void stockUpdate(StockStatus stockStatus);
	boolean aNewStockFileHasBeenFound();
	String getNextFileName();
}