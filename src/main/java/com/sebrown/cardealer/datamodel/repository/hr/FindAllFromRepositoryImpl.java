package com.sebrown.cardealer.datamodel.repository.hr;

import org.springframework.data.repository.CrudRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Steve Brown
 *
 * @param <T> Entity type.
 * @param <ID> Entity ID column type.
 */
@RequiredArgsConstructor
public class FindAllFromRepositoryImpl <T, ID> implements FindAllFromRepository<T, ID>{

	private final CrudRepository<T, ID> repo;
		
	@Override
	public Iterable<T> findAll() {
		return repo.findAll();
	}
}
