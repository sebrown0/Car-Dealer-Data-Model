package com.sebrown.cardealer.datamodel.service.stock;

import java.util.List;

/**
 * 
 * @author Steve Brown
 * 
 * Get a list of type<T> for the specified strategy.
 *
 * @param <T> type of the List.
 */
public class FinderListByStrategy <T>{
	public List<T> find(StrategyListFinder<T> strategy){
		return strategy.execute();
	}
}
