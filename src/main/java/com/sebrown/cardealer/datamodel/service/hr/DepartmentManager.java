/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import org.springframework.stereotype.Component;

/**
 * @author Steve Brown
 *
 */
@Component
public interface DepartmentManager {
	int getManagerId();
	String getDepartmentName();
	String getFirstName();
	String getLastName();
}
