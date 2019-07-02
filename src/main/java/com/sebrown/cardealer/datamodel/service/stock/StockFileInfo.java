/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import org.springframework.stereotype.Component;

/**
 * @author Steve Brown
 *
 */
@Component
public interface StockFileInfo {
	int getFileNum();
	String getFileName();
	String getFilePath();
}
