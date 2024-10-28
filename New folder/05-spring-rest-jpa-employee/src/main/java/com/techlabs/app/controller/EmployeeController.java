package com.techlabs.app.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.entity.Employee;
import com.techlabs.app.service.EmployeeService;
import com.techlabs.app.util.PagedResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		employeeDTO.setId(0);
		EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<PagedResponse<Employee>> getAllEmployees( 
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction) {
		
		System.out.println(page+" "+size+" "+sortBy+" "+direction);
			
		 PagedResponse<Employee> allEmployees = employeeService.getAllEmployees(page,size,sortBy,direction);
		return new ResponseEntity<>(allEmployees, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		return employee.map(emp -> new ResponseEntity<>(emp, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/active")
	public ResponseEntity<List<Employee>> getActiveEmployees() {
		List<Employee> employees = employeeService.getActiveEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable String name) {
		List<Employee> employees = employeeService.getEmployeesByName(name);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/count-by-designation")
	public ResponseEntity<List<Object[]>> countEmployeesByDesignation() {
		List<Object[]> countData = employeeService.countEmployeesByDesignation();
		return new ResponseEntity<>(countData, HttpStatus.OK);
	}
}
