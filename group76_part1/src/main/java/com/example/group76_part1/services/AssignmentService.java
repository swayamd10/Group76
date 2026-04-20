package com.example.group76_part1.services;

import com.example.group76_part1.dtos.AssignmentRequest;
import com.example.group76_part1.dtos.AssignmentResponse;
import com.example.group76_part1.entities.Assignment;
import com.example.group76_part1.entities.Department;
import com.example.group76_part1.entities.Employee;
import com.example.group76_part1.exceptions.ResourceNotFoundException;
import com.example.group76_part1.repos.AssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             EmployeeService employeeService,
                             DepartmentService departmentService) {
        this.assignmentRepository = assignmentRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public AssignmentResponse createAssignment(AssignmentRequest request) {
        Employee employee = employeeService.findEmployee(request.getEmployeeId());
        Department department = departmentService.findDepartment(request.getDepartmentId());

        Assignment assignment = new Assignment();
        assignment.setEmployee(employee);
        assignment.setDepartment(department);

        assignment.setRole(
                request.getRole() == null || request.getRole().isBlank()
                        ? "Member"
                        : request.getRole()
        );

        assignment.setAccessLevel(
                request.getAccessLevel() == null || request.getAccessLevel().isBlank()
                        ? "Standard"
                        : request.getAccessLevel()
        );

        return toResponse(assignmentRepository.save(assignment));
    }

    public void deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id " + id));

        assignmentRepository.delete(assignment);
    }

    private AssignmentResponse toResponse(Assignment assignment) {
        return new AssignmentResponse(
                assignment.getId(),
                assignment.getEmployee().getId(),
                assignment.getEmployee().getName(),
                assignment.getDepartment().getId(),
                assignment.getDepartment().getName(),
                assignment.getRole(),
                assignment.getAccessLevel()
        );
    }
}