/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.dto.EmployeeAttendanceData;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAttendance;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeAttendanceRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;
import com.sebrown.cardealer.datamodel.util.BusinessDaysCalculator;

import lombok.NoArgsConstructor; 

/**
 * @author Steve Brown
 *
 *	Record an employee's being absent for a SaveAttendanceStrategy strategy.
 */
@Transactional
@Service
@NoArgsConstructor
public class RecordEmployeeAbsentServiceImpl implements RecordEmployeeAbsentService{

	@Autowired
	EmployeeFinderRepository empFinderRepo;
	
	@Autowired
	EmployeeAttendanceRepository empAbsentRepo;
		
	@Autowired
	SetEmployeeAttendanceYearImpl empAttYear;	
	
	private EmployeeAttendanceData empAttData;
	private EmployeeAttendance newEmpAttRecord = null;
	private Employee theEmployee;
	
	@Override
	public EmployeeAttendance recordAbsentEvent(EmployeeAttendanceData empAttData, StrategySaveAttendance strategy) {
		this.empAttData = empAttData;
		if(setEmployeeOk()) {
			setAttendanceYearForEmployee();
			setNumberOfDaysAbsent();
			newEmpAttRecord = new ContextSaveEmployeeAttendanceRecord(strategy, empAttData).update();
		}
		return newEmpAttRecord;
	}

	private boolean setEmployeeOk() {
		theEmployee = empFinderRepo.findById(empAttData.getEmpId()).orElse(null);
		return (theEmployee == null) ? false : true; 
	}
		
	private void setAttendanceYearForEmployee() {
		empAttData.setAttendanceYear(
				empAttYear.setAttendanceYear(
						new FindEmployeeById(empAttData.getEmpId(), empFinderRepo), empAttData.getStartDate()));
	}
	
	private void setNumberOfDaysAbsent() {
		empAttData.setNumDays(BusinessDaysCalculator.calculateDaysBetweenDates(
				empAttData.getStartDate(), empAttData.getEndDate()));
	}
}
