/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import lombok.RequiredArgsConstructor;

/**
 * @author Steve Brown
 *
 * Get an employee's days absent for a strategy and year.
 */
@RequiredArgsConstructor
public class ContextEmployeeAttendanceRecord {
	
	private final StrategyEmployeeAttendanceRecord strategy;
	
	public long getDaysAbsent() {
		return strategy.getDaysAbsentForYear();
	}
}
