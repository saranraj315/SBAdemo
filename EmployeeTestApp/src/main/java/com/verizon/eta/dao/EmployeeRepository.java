package com.verizon.eta.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.eta.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
