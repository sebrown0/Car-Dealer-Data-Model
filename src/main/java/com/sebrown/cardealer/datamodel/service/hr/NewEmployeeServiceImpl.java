/**
 * 
 */
package com.sebrown.cardealer.datamodel.service.hr;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sebrown.cardealer.datamodel.dto.EmployeeData;
import com.sebrown.cardealer.datamodel.dto.ManagerData;
import com.sebrown.cardealer.datamodel.model.hr.Department;
import com.sebrown.cardealer.datamodel.model.hr.Employee;
import com.sebrown.cardealer.datamodel.model.hr.RoleAndSeniority;
import com.sebrown.cardealer.datamodel.model.hr.Seniority;
import com.sebrown.cardealer.datamodel.repository.hr.DepartmentRepository;
import com.sebrown.cardealer.datamodel.repository.hr.EmployeeFinderRepository;
import com.sebrown.cardealer.datamodel.repository.hr.RoleAndSeniorityRepository;

import lombok.AllArgsConstructor; 

/**
 * @author Steve Brown
 *
 * Persist a new employee to the data source if the employee's data is valid. 
 */
@Transactional
@Service
@AllArgsConstructor
public class NewEmployeeServiceImpl implements NewEmployeeService{

	@PersistenceContext(unitName = "default")
	private EntityManager em;
	
	private EmployeeFinderRepository empRepo;
	private DepartmentRepository deptRepo;
	private RoleAndSeniorityRepository rasRepo;
	private DepartmentManagerService deptManagerService;
		
	/*
	 *  Persist a new employee to the Data Source if the employee's data is valid.
	 */
	@Override
	public Employee saveNewEmployee(EmployeeData newEmpDto) {		
		Employee newEmp = null;
		Department department = deptRepo.findByDeptName(newEmpDto.getDeptName());
		RoleAndSeniority ras = rasRepo.findRoleAndSeniority(newEmpDto.getRoleName(), newEmpDto.getSeniority());
		ManagerData manager = new ManagerData(deptManagerService.getDepartmentManager(department.getDeptId()));

		if(employeeDataIsOk(ras, newEmpDto)) {
			newEmp = buildNewEmployee(newEmpDto, department, ras, manager);
			em.persist(newEmp);
		}		
		return newEmp;
	}
	
	/*
	 * Validate employee data.
	 */
	private boolean employeeDataIsOk(RoleAndSeniority ras, EmployeeData newEmpDto) {
		if(employeeSalaryInTheCorrectRange(ras, newEmpDto.getSalary()) && employeeSsnValid(newEmpDto)
				&& employeeNameValid(newEmpDto) && employeeDobValid(newEmpDto) && employeeHireDateValid(newEmpDto)) {
			return true;
		}
		return false;
	}

	/*
	 * If the employee's hire date has not been set, set it to current date. 
	 */
	private boolean employeeHireDateValid(EmployeeData newEmpDto) {
		if(newEmpDto.getHireDate() == null) {
			newEmpDto.setHireDate(Calendar.getInstance());
		}
		return true;
	}

	/*
	 *  Check that the salary is above min salary and below max salary.
	 */
	@Override
	public boolean employeeSalaryInTheCorrectRange(RoleAndSeniority ras, BigDecimal salary) {
		Seniority s = rasRepo.findSeniority(ras.getRasId());
		return (salary.compareTo(s.getSalaryMin()) >= 0) ? (salary.compareTo(s.getSalaryMax()) <= 0) ? true : false : false;
	}
	
	/*
	 *  Basic check to see if there is some sort of name.
	 */
	private boolean employeeNameValid(EmployeeData newEmpDto) {
		int nameLen = (newEmpDto.getFirstName().length() + newEmpDto.getLastName().length());
		return (nameLen >= 2) ? true : false;
	}
	
	/*
	 *  Basic check to see if DOB is less than the current date.
	 */
	private boolean employeeDobValid(EmployeeData newEmpDto) {
		return (newEmpDto.getDob().compareTo(Calendar.getInstance()) < 0) ? true : false ;
	}
	
	/*
	 *  Check that the ssn doesn't already exist.
	 *  A null result indicates that the ssn doesn't exist so it's ok to insert.
	 */
	private boolean employeeSsnValid(EmployeeData newEmpDto) {
		return (empRepo.findEmployeeBySsn(newEmpDto.getSsn()) == null) ? true : false;
	}
	
	/**
	 * Convert the DTO into an employee entity. 
	 * Map the available fields with model mapper and set the rest.
	 */
	private Employee buildNewEmployee(EmployeeData newEmpDto, Department department, 
			RoleAndSeniority ras, ManagerData manager) {

		ModelMapper mapper = new ModelMapper();
		Employee e = mapper.map(newEmpDto, Employee.class);
		e.setDepartment(department);
		e.setManagerId(manager.getManagerId());
		e.setRas(ras);
		e.setAnnualLeave(ras.getSeniority().getHolidayEntitlement());
		return e;
	}
}
