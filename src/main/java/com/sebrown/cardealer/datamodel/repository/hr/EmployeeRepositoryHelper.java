/**
 * 
 */
package com.sebrown.cardealer.datamodel.repository.hr;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sebrown.cardealer.datamodel.dto.EmployeeDto;
import com.sebrown.cardealer.datamodel.model.hr.AbsentYear;
import com.sebrown.cardealer.datamodel.model.hr.Department;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.EmployeeAbsent;
import com.sebrown.cardealer.datamodel.model.hr.RoleAndSeniority;
import com.sebrown.cardealer.datamodel.model.hr.Seniority;
import com.sebrown.cardealer.datamodel.util.GenericBuilder;

import lombok.AllArgsConstructor; 

/**
 * @author Steve Brown
 *
 */
@Repository
@Transactional
@AllArgsConstructor
public class EmployeeRepositoryHelper {

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	private EmployeeRepository empRepo;
	private DepartmentRepository deptRepo;
	private RoleAndSeniorityRepository rasRepo;
	private AbsentYearRepository absYearRepo;
	private EmployeeAbsentRepository empAbsentRepo;
	
	public List<String> getEmployeeNames() {
		return em.createNamedQuery("getEmpsNames", String.class).getResultList();
	}
	
	/*
	 *  Persist a new employee to the Data Source and
	 *  then updated associated data/tables.
	 */
	public Employee saveNewEmployee(EmployeeDto newEmpDto) {
		Employee newEmp = null;
		Department d = deptRepo.findByDeptName(newEmpDto.getDeptName());
		RoleAndSeniority ras = rasRepo.findRoleAndSeniority(newEmpDto.getRoleName(), newEmpDto.getSeniority());
		
		if(employeeDataIsOk(ras, newEmpDto)) {
			newEmp = GenericBuilder.of(Employee::new)
					.with(Employee::setDepartment, d)
					.with(Employee::setRas, ras)
					.with(Employee::setFirstName, newEmpDto.getFirstName())
					.with(Employee::setLastName, newEmpDto.getLastName())
					.with(Employee::setDob, newEmpDto.getDob())
					.with(Employee::setHireDate, newEmpDto.getHireDate())
					.with(Employee::setSalary, newEmpDto.getSalary())
					.with(Employee::setSsn, newEmpDto.getSsn())
					.with(Employee::setAnnualLeave, ras.getSeniority().getHolidayEntitlement())
					.build();
			em.persist(newEmp);
		}		
		return newEmp;
	}
	
	/*
	 * Validate employee data.
	 */
	private boolean employeeDataIsOk(RoleAndSeniority ras, EmployeeDto newEmpDto) {
		if(employeeSalaryInTheCorrectRange(ras, newEmpDto.getSalary()) && employeeSsnValid(newEmpDto)
				&& employeeNameValid(newEmpDto) && employeeDobValid(newEmpDto)) {
			return true;
		}
		return false;
	}

	/*
	 *  Check that the salary is above min salary and below max salary.
	 */
	public boolean employeeSalaryInTheCorrectRange(RoleAndSeniority ras, BigDecimal salary) {
		Seniority s = rasRepo.findSeniority(ras.getRasId());
		return (salary.compareTo(s.getSalaryMin()) >= 0) ? (salary.compareTo(s.getSalaryMax()) <= 0) ? true : false : false;
	}
	
	/*
	 *  Basic check to see if there is some sort of name.
	 */
	private boolean employeeNameValid(EmployeeDto newEmpDto) {
		int nameLen = (newEmpDto.getFirstName().length() + newEmpDto.getLastName().length());
		return (nameLen >= 2) ? true : false;
	}
	
	/*
	 *  Basic check to see if DOB is less than the current date.
	 */
	private boolean employeeDobValid(EmployeeDto newEmpDto) {
		return (newEmpDto.getDob().compareTo(Calendar.getInstance()) < 0) ? true : false ;
	}
	
	/*
	 *  Check that the ssn doesn't already exist.
	 *  A null result indicates that the ssn doesn't exist so it's ok to insert.
	 */
	private boolean employeeSsnValid(EmployeeDto newEmpDto) {
		return (empRepo.findEmployeeBySsn(newEmpDto.getSsn()) == null) ? true : false;
	}
	
	/*
	 * Record an employee's absenteeism.
	 */
	public EmployeeAbsent recordEmployeeAbsent(int empId, LocalDate startDate, LocalDate endDate, String reason) {
		EmployeeAbsent empAbs = null;
		Employee emp = empRepo.findById(empId).orElse(null);
		if(emp != null) {
			AbsentYear absYear = findAbsentYear(emp, startDate);
			long numDays = calculateNumberOfDaysAbsentInclusive(startDate, endDate);
			if(numDays > 0 ) {
				if(checksForAnnualLeaveAreOk(reason, absYear, numDays, emp.getEmpId())) {
					empAbs = GenericBuilder.of(EmployeeAbsent::new)
							.with(EmployeeAbsent::setEmpAbsentId, absYear.getAbsentId())
							.with(EmployeeAbsent::setAbsentStartDate, startDate)
							.with(EmployeeAbsent::setAbsentEndDate, endDate)
							.with(EmployeeAbsent::setAbsentYear, absYear)
							.with(EmployeeAbsent::setNumDays, numDays)
							.with(EmployeeAbsent::setReason, reason)
							.build();
					empAbsentRepo.save(empAbs);
				}
			}
		}
		return empAbs;
	}
	
	/**
	 * Find the year for an employee's absenteeism.
	 * If the employee has no absent record for the year, create one.
	 */
	private AbsentYear findAbsentYear(Employee emp, LocalDate startDate) {	
		AbsentYear absentYear = absYearRepo.findByEmployeeIdAndYear(emp.getEmpId(), (short)startDate.getYear());
		if(absentYear == null) {
			absentYear = createAbsentYear(emp, startDate);
		}
		return absentYear;
	}

	/*
	 * There's not a record for the employee, for this year so create one.
	 */
	private AbsentYear createAbsentYear(Employee emp, LocalDate startDate) {
		AbsentYear absentYear = new AbsentYear();
		absentYear.setEmployee(emp);
		absentYear.setYear((short)startDate.getYear());
		em.persist(absentYear);
		return absentYear;
	}			
	
	/*
	 * Calculates the number of business days between 2 dates (inclusive).
	 * Doesn't work for public holidays.
	 */
	public long calculateNumberOfDaysAbsentInclusive(LocalDate startDate, LocalDate endDate) {
		long businessDays = 0;
		long numDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);				
		if(numDaysBetween > 0) {
			businessDays = IntStream.iterate(0, i -> i + 1).limit(numDaysBetween).mapToObj(startDate::plusDays)
				.filter(d -> Stream.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
				.anyMatch(d.getDayOfWeek()::equals)).count() + 1;
		}
		return businessDays;
	}
	
	/*
	 * Is the reason for being absent annual leave.
	 */
	private boolean checksForAnnualLeaveAreOk(String reason, AbsentYear absYear, long numDaysRequested, int empId) {
		if(reason.compareTo("Annual Leave") == 0) {
			return (checkAnnualLeaveRequest(absYear, numDaysRequested, empId)) ? true : false;
		}
		return true;
	}	
	
	/*
	 * Calculate if the requested amount of annual leave exceeds allowance.
	 * Return true if request OK. 
	 */
	public boolean checkAnnualLeaveRequest(AbsentYear absYear, long numDaysRequested, int empId) {
		if(employeeDoesNotHaveAnAbsentRecordForThisYear(absYear, empId)) {
			return true;
		}
		return checkEmployeesAnnualLeaveRecordForYear(absYear, numDaysRequested, empId);
	}
	
	/*
	 * Check to see if the employee has a record for this year.
	 * If the employee does not have a record the annual leave request can be granted.
	 */
	private boolean employeeDoesNotHaveAnAbsentRecordForThisYear(AbsentYear absYear, int empId) {
		List<EmployeeAbsent> empAbs = empAbsentRepo.findEmployeeAbsentRecordsForYear(absYear.getYear(), empId);
		return (empAbs.isEmpty()) ? true : false;
	}
	
	/*
	 * Find out how many days annual leave the employee has taken for the year.
	 * If the number of days requested are greater than those remaining, deny request. 
	 */
	private boolean checkEmployeesAnnualLeaveRecordForYear(AbsentYear absYear, long numDaysRequested, int empId) {
		long numDaysAbsent = empAbsentRepo.numDaysEmployeeHasBeenAbsent(absYear, "Annual Leave", empId);
		long annualLeave = rasRepo.findSeniority(empId).getHolidayEntitlement();
		long diff = (annualLeave - numDaysAbsent) - numDaysRequested;
		return (diff >= 0 && diff <= annualLeave ) ? true : false;
	}
}
