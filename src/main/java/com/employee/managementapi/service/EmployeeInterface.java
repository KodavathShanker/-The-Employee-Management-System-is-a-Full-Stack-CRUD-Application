package com.employee.managementapi.service;

import java.util.List;

import com.employee.managementapi.entity.Employee;

public interface EmployeeInterface  {

	 	Employee postEmployee(Employee employee);

	    List<Employee> getAllEmployees();

	    Employee getEmployeeById(Long id);

	    Employee updateEmployee(Long id, Employee employee);

	    void deleteEmployee(Long id);
}
