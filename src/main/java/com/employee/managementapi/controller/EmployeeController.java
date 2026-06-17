package com.employee.managementapi.controller;

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

import com.employee.managementapi.entity.Employee;
import com.employee.managementapi.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EmployeeController {

	 @Autowired
	    private EmployeeService empService;

	    // CREATE EMPLOYEE
	    // POST : http://localhost:8080/api/employee
	    @PostMapping("/employee")
	    public ResponseEntity<Employee> postEmployee(
	            @RequestBody Employee employee) {

	        Employee savedEmployee = empService.postEmployee(employee);

	        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	    }

	    // GET ALL EMPLOYEES
	    // GET : http://localhost:8080/api/employees
	    @GetMapping("/employees")
	    public ResponseEntity<List<Employee>> getAllEmployees() {

	        List<Employee> employees = empService.getAllEmployees();

	        return ResponseEntity.ok(employees);
	    }

	    // GET EMPLOYEE BY ID
	    // GET : http://localhost:8080/api/employee/1
	    @GetMapping("/employee/{id}")
	    public ResponseEntity<?> getEmployeeById(
	            @PathVariable Long id) {

	        try {

	            Employee employee = empService.getEmployeeById(id);

	            return ResponseEntity.ok(employee);

	        } catch (EntityNotFoundException e) {

	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body(e.getMessage());
	        }
	    }

	    // UPDATE EMPLOYEE
	    // PUT : http://localhost:8080/api/employee/1
	    @PutMapping("/employee/{id}")
	    public ResponseEntity<?> updateEmployee(
	            @PathVariable Long id,
	            @RequestBody Employee employee) {

	        try {

	            Employee updatedEmployee =
	                    empService.updateEmployee(id, employee);

	            return ResponseEntity.ok(updatedEmployee);

	        } catch (EntityNotFoundException e) {

	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body(e.getMessage());

	        } catch (Exception e) {

	            return ResponseEntity
	                    .status(HttpStatus.BAD_REQUEST)
	                    .body("Unable to update employee");
	        }
	    }

	    // DELETE EMPLOYEE
	    // DELETE : http://localhost:8080/api/employee/1
	    @DeleteMapping("/employee/{id}")
	    public ResponseEntity<?> deleteEmployee(
	            @PathVariable Long id) {

	        try {

	            empService.deleteEmployee(id);

	            return ResponseEntity.ok(
	                    "Employee with ID " + id +
	                    " deleted successfully");

	        } catch (EntityNotFoundException e) {

	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body(e.getMessage());
	        }
	    }
}
