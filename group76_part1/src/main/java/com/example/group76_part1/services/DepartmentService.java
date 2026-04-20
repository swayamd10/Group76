package com.example.group76_part1.services;

import com.example.group76_part1.dtos.DepartmentRequest;
import com.example.group76_part1.dtos.DepartmentResponse;
import com.example.group76_part1.entities.Department;
import com.example.group76_part1.exceptions.ResourceNotFoundException;
import com.example.group76_part1.repos.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setBudget(request.getBudget());
        department.setLocation(request.getLocation());

        return toResponse(departmentRepository.save(department));
    }

    public Department findDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
    }

    private DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getBudget(),
                department.getLocation()
        );
    }
}