package com.sebrown.cardealer.datamodel.mapper;

import java.util.HashMap;
import java.util.Map;

import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;
import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepository;

/**
 * 
 * @author Steve Brown
 *
 *	Map the Manufacturer repository to a map.
 */
public class MapperManufacturerToMap implements MapperRepositoryToMap<String, Manufacturer, Long>{

	private final Map<String, Manufacturer> mapping = new HashMap<String, Manufacturer>();
	
	@Override
	public void accept(FindAllFromRepository<Manufacturer, Long> r) {
		r.findAll().forEach(m -> mapping.put(m.getName(), m));	
	}
	
	@Override
	public Map<String, Manufacturer> map(FindAllFromRepository<Manufacturer, Long> finder) {
		accept(finder);
		return mapping;
	}
}
