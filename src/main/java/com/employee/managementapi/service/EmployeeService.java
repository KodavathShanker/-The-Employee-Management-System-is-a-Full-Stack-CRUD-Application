package com.employee.managementapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.managementapi.entity.Employee;
import com.employee.managementapi.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService implements EmployeeInterface{

	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee postEmployee(Employee employee) {

    	employee.setId(null);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Employee not found with ID : " + id));
    }

    @Override
    public Employee updateEmployee(Long id,
                                   Employee employee) {

        Employee existingEmployee =
                employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Employee not found with ID : " + id));

        // Update fields
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPhone(employee.getPhone());
       

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee =
                employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Employee not found with ID : " + id));

        employeeRepository.delete(employee);
    }
}
