package com.verizon.eta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verizon.eta.model.Employee;

@Service
public interface EmployeeService {
	
	Employee getEmployeeById(long id);
	
	List<Employee> getAllEmployees();
	
	Employee addEmployee(Employee e);
	
	Employee updateEmployee(Employee e);
	
	boolean deleteEmployeeById(long id);
	
}
