/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

/**
 * @author Steve Brown
 *
 */
public interface StockFileFinder {
	
	/**
	 *	Finds the next stock file to read. If there is one.
	 * 
	 * 	@return the details of the next file to read. Null if no file.
	 */
	StockFileNext checkForNewFile();
}
