/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.stock;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebrown.cardealer.datamodel.dto.CarData;

/**
 * @author Steve Brown
 *
 */
@Component
public class StockFileReader {

	/**
	 * Read the JSON file into a list of Car Data (DTOs).
	 * 
	 * @param fileInfo: file info for the file to be read.
	 * @return a list of car data (DTOs).
	 */
	public static List<CarData> read(StockFileInfo fileInfo) {
		List<CarData> carData = null;	
		ObjectMapper mapper = new ObjectMapper();
		try {
			carData = mapper.reader()
					.forType(new TypeReference<List<CarData>>(){})
					.readValue(new File(fileInfo.getFilePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return carData;
	}
}
