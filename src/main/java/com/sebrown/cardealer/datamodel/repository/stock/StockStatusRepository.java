/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.stock;

import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.stock.StockStatus;

/**
 * @author Steve Brown
 *
 */
public interface StockStatusRepository extends CrudRepository<StockStatus, Integer>{
	StockStatus findStockStatusByStatus(String status);
	default StockStatusRepository getRepository() { return this; }
}
