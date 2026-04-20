package com.example.group76_part1.services;

import com.example.group76_part1.dtos.AssignmentRequest;
import com.example.group76_part1.dtos.AssignmentResponse;
import com.example.group76_part1.entities.Assignment;
import com.example.group76_part1.entities.Department;
import com.example.group76_part1.entities.Employee;
import com.example.group76_part1.repos.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private AssignmentService assignmentService;

    @Test
    void createAssignment_ShouldUseDefaultRoleAndAccessLevel_WhenBlankValuesProvided() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Alice");
        employee.setContractType("FULL_TIME");
        employee.setStartDate(LocalDate.now().minusMonths(12));
        employee.setSalary(30000.0);
        employee.setEmail("alice@test.com");
        employee.setAddress("Liverpool");
        employee.setRank("JUNIOR");

        Department department = new Department();
        department.setId(2L);
        department.setName("Logistics");
        department.setBudget(100000.0);
        department.setLocation("Liverpool");

        AssignmentRequest request = new AssignmentRequest();
        request.setEmployeeId(1L);
        request.setDepartmentId(2L);
        request.setRole("");
        request.setAccessLevel("");

        when(employeeService.findEmployee(1L)).thenReturn(employee);
        when(departmentService.findDepartment(2L)).thenReturn(department);
        when(assignmentRepository.save(any(Assignment.class))).thenAnswer(invocation -> {
            Assignment assignment = invocation.getArgument(0);
            assignment.setId(50L);
            return assignment;
        });

        AssignmentResponse response = assignmentService.createAssignment(request);

        assertEquals("Member", response.role());
        assertEquals("Standard", response.accessLevel());
        assertEquals("Alice", response.employeeName());
        assertEquals("Logistics", response.departmentName());
    }
}