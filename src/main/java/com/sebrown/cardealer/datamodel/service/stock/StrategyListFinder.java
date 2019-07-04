package com.sebrown.cardealer.datamodel.service.stock;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Steve Brown
 *
 * @param <T> the type of list to return when the strategy is executed.
 */
@Component
public interface StrategyListFinder <T> {
	List<T> execute();
}
