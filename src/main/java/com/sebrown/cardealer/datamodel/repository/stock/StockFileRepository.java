/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sebrown.cardealer.datamodel.model.stock.StockFile;

/**
 * @author Steve Brown
 *
 */
public interface StockFileRepository extends CrudRepository<StockFile, Integer>{
	
	@Query("SELECT max(f.fileId) FROM StockFile f")
	int findLastStockFileNum();
}
