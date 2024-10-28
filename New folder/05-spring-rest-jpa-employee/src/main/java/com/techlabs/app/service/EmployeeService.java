package com.techlabs.app.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.entity.Employee;
import com.techlabs.app.util.PagedResponse;

import jakarta.validation.Valid;

public interface EmployeeService {

//	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeById(int id);

	public void deleteEmployee(int id);

	public EmployeeDTO createEmployee(@Valid EmployeeDTO employeeDTO);

	public List<Employee> getActiveEmployees();

	public List<Employee> getEmployeesByName(String name);

	public List<Object[]> countEmployeesByDesignation();

	
	public PagedResponse<Employee> getAllEmployees(int page, int size, String sortBy, String direction);

}
