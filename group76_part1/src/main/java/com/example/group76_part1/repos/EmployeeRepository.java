package com.example.group76_part1.repos;

import com.example.group76_part1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find an employee by their email — useful for lookup and uniqueness checks
    Optional<Employee> findByEmail(String email);

    // Find all employees on a given contract type e.g. "FULL_TIME"
    List<Employee> findByContractType(String contractType);
}
