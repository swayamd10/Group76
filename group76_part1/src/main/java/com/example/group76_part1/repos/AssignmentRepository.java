package com.example.group76_part1.repos;

import com.example.group76_part1.entities.Assignment;
import com.example.group76_part1.entities.Department;
import com.example.group76_part1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // Find all assignments belonging to a specific department
    List<Assignment> findByDepartment(Department department);

    // Find all assignments belonging to a specific employee
    List<Assignment> findByEmployee(Employee employee);

    // Find all assignments at a given access level
    List<Assignment> findByAccessLevel(String accessLevel);
}
