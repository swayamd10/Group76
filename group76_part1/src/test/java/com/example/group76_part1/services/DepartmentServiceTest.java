package com.example.group76_part1.services;

import com.example.group76_part1.dtos.DepartmentRequest;
import com.example.group76_part1.repos.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Unit test for Department service

@ExtendWith(MockitoExtension.class) // Enables Mockito
class DepartmentServiceTest {

    // Mock repository
    @Mock
    private DepartmentRepository departmentRepository;

    // Injects mock repository into department service
    @InjectMocks
    private DepartmentService departmentService;

    // Test to verify department creation
    @Test
    void createDepartment_ShouldReturnSavedDepartment() {
        // Create request object with the test data
        DepartmentRequest request = new DepartmentRequest();
        request.setName("HR");
        request.setBudget(50000.0);
        request.setLocation("London");

        // Mock repository saves the behaviour
        when(departmentRepository.save(any())).thenAnswer(invocation -> {
            var department = invocation.getArgument(0, com.example.group76_part1.entities.Department.class);
            department.setId(1L);
            return department;
        });

        // Calls the service method to get a response
        var response = departmentService.createDepartment(request);

        // Assertions to verify the correct data mapping
        assertEquals(1L, response.id());
        assertEquals("HR", response.name());
        assertEquals(50000.0, response.budget());
        assertEquals("London", response.location());
    }
}