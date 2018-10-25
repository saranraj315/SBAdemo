package com.verizon.eta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.eta.dao.EmployeeRepository;
import com.verizon.eta.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Employee getEmployeeById(long id) {
		
		Employee emp = null;
		
		Optional<Employee> optEmployee = employeeRepo.findById(id);
		if(optEmployee.isPresent())
			emp = optEmployee.get();
		
		return emp;
	}

	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	
	@Override
	public Employee addEmployee(Employee e) {
		return employeeRepo.save(e);
	}

	@Override
	public Employee updateEmployee(Employee e) {
		return employeeRepo.save(e);
	}

	@Override
	public boolean deleteEmployeeById(long id) {

		boolean isDeleted = false;
		
		if(employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
			isDeleted = true;
		}
		
		return isDeleted;
	}

}
