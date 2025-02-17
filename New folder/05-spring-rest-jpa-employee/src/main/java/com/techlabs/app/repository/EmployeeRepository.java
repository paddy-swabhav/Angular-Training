package com.techlabs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techlabs.app.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByActive(boolean active);

	List<Employee> findByName(String name);

	@Query("SELECT e.designation, COUNT(e) FROM Employee e GROUP BY e.designation")
	List<Object[]> countEmployeesByDesignation();
}
