package com.example.group76_part1.repos;

import com.example.group76_part1.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find a department by its name e.g. "Engineering"
    Optional<Department> findByName(String name);

    // Find all departments in a given location e.g. "London"
    List<Department> findByLocation(String location);
}
