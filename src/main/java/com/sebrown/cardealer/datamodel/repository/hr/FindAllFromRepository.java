package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Steve Brown
 *
 * @param <T> Entity type.
 * @param <ID> Entity ID column type.
 */
@Service
public interface FindAllFromRepository <T, ID> {
	Iterable<T> findAll();
}
