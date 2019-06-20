/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.model.Manufacturer;

/**
 * @author Steve Brown
 *
 */
@Repository
@Transactional
public class ManufacturerRepository {

	@Autowired
	EntityManager em;
	
	public Manufacturer save(Manufacturer m) {
		if(m.getId() == null) {
			em.persist(m);
		}else {
			em.merge(m);
		}
		return m;
	}
	
	public Manufacturer findManById(Long id) {
		return em.find(Manufacturer.class, id);
	}
}
