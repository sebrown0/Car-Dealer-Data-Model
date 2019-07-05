/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import org.springframework.stereotype.Component;

/**
 * @author Steve Brown
 *
 * Information about a stock file that has been read
 * or attempted to be read.
 */
@Component
public interface StockFileInfo {
	int getFileNum();
	String getFileName();
	String getFilePath();
	boolean stockFileFound();
}
