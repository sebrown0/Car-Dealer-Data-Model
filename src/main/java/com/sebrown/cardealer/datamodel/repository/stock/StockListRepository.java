/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.stock.Car;
import com.sebrown.cardealer.datamodel.model.stock.StockList;
import com.sebrown.cardealer.datamodel.model.stock.StockStatus;

/**
 * @author Steve Brown
 *
 */
public interface StockListRepository extends CrudRepository<StockList, Integer>{
	
	@Query("SELECT l FROM StockList l WHERE l.stockStatus=:status")
	StockList findStockListForADefinedStatus(StockStatus status);
	
	@Query("SELECT s.cars FROM StockList l, StockStatus s WHERE l.stockStatus=:status")
	List<Car> findCarsForADefinedStockStatus(StockStatus status);
	
}
