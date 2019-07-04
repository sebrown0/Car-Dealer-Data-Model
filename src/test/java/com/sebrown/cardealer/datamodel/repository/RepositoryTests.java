package com.sebrown.cardealer.datamodel.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sebrown.cardealer.datamodel.mapper.MapperManufacturerToMap;
import com.sebrown.cardealer.datamodel.mapper.MapperRepositoryToMap;
import com.sebrown.cardealer.datamodel.model.stock.Manufacturer;
import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepository;
import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepositoryImpl;
import com.sebrown.cardealer.datamodel.repository.hr.ManufacturerRepository;

/*
 * Tests for Repositories.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {
	
	@Autowired
	ManufacturerRepository manRepo;
		
	@Test
	public void findAllFromRepository() {
		FindAllFromRepository<Manufacturer, Long> finder = new FindAllFromRepositoryImpl<Manufacturer, Long>(manRepo);
		assertNotNull(finder.findAll());
	}
	
	@Test
	public void mapperManufacturerRepositoryToMap() {
		FindAllFromRepository<Manufacturer, Long> finder = new FindAllFromRepositoryImpl<Manufacturer, Long>(manRepo);
		MapperRepositoryToMap<String, Manufacturer, Long> mapper = new MapperManufacturerToMap();
		String name = mapper.map(finder).get("Ford").getName();
		assertEquals("Ford", name);
	}
}
