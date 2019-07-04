package com.sebrown.cardealer.datamodel.mapper;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.repository.hr.FindAllFromRepository;

/**
 * @author Steve Brown
 *
 * @param <K> Key type for map.   
 * @param <T> Entity type.
 * @param <ID> Entity ID column type.
 */
@Service
public interface MapperRepositoryToMap <K, T, ID> extends Consumer<FindAllFromRepository<T, ID>> {
	Map<K, T> map(FindAllFromRepository <T, ID> finder);
}
