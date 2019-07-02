/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

/**
 * @author Steve Brown
 *
 * Interface for a strategy to find how many days
 * an employee has been absent for a year.
 * 
 * The strategy is the reason the employee was absent.
 */
public interface StrategyEmployeeAttendanceRecord {
	long getDaysAbsentForYear();
}
