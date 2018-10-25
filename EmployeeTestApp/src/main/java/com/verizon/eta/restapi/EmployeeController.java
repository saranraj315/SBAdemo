package com.verizon.eta.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.eta.model.Employee;
import com.verizon.eta.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
	
		ResponseEntity<Employee> resp;
		Employee employee = employeeService.getEmployeeById(id);
		
		if(employee == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(employee, HttpStatus.OK);
		
		return resp;
		
	}
	
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		
		ResponseEntity<Employee> resp = null;
		
		if(resp == null) {
			Employee emp = employeeService.addEmployee(employee);
			if(emp == null)
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<>(emp, HttpStatus.OK);
		}
		
		return resp;
		
	}
	
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		
		ResponseEntity<Employee> resp = null;
		Employee emp;
		
		if(resp == null) {
			emp = employeeService.updateEmployee(employee);
			if(emp == null)
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<>(emp, HttpStatus.OK);
		}
		
		return resp;
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id) {
		ResponseEntity<Void> resp = null;
		
		if(employeeService.deleteEmployeeById(id))
			resp = new ResponseEntity<>(HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return resp;
	}
	
	
}
