package com.techlabs.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.entity.Employee;
import com.techlabs.app.repository.EmployeeRepository;
import com.techlabs.app.util.PagedResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = convertEmployeeDtoToObject(employeeDTO);
		Employee newEmployee = employeeRepository.save(employee);
		EmployeeDTO empDTO = convertEmployeeObjectToDTO(employee);
		return empDTO;
	}

	private Employee convertEmployeeDtoToObject(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		if(employeeDTO.getId()!=0) {
			employee.setId(employeeDTO.getId());
		}
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setDesignation(employeeDTO.getDesignation());
		employee.setSalary(employeeDTO.getSalary());
		employee.setActive(employeeDTO.isActive());
		return employee;
	}
	
	private EmployeeDTO convertEmployeeObjectToDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if(employee.getId()!=0) {
			employeeDTO.setId(employee.getId());
		}
		employeeDTO.setName(employee.getName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setSalary(employee.getSalary());
		employeeDTO.setActive(employee.isActive());
		return employeeDTO;
	}
	
	
//	@Override
//	public List<Employee> getAllEmployees() {
//		return employeeRepository.findAll();
//	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> getActiveEmployees() {
		return employeeRepository.findByActive(true);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public List<Object[]> countEmployeesByDesignation() {
		return employeeRepository.countEmployeesByDesignation();
	}
	
	public PagedResponse<Employee> getAllEmployees(int page, int size, String sortBy, String direction) {
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		
	    Page<Employee> employeePage = employeeRepository.findAll(pageable);
	    
	    return new PagedResponse<Employee>(employeePage.getContent(), employeePage.getNumber(), employeePage.getSize(), employeePage.getTotalElements(), employeePage.getTotalPages(), employeePage.isLast());
	}


}