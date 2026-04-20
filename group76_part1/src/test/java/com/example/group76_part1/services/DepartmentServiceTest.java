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

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void createDepartment_ShouldReturnSavedDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("HR");
        request.setBudget(50000.0);
        request.setLocation("London");

        when(departmentRepository.save(any())).thenAnswer(invocation -> {
            var department = invocation.getArgument(0, com.example.group76_part1.entities.Department.class);
            department.setId(1L);
            return department;
        });

        var response = departmentService.createDepartment(request);

        assertEquals(1L, response.id());
        assertEquals("HR", response.name());
        assertEquals(50000.0, response.budget());
        assertEquals("London", response.location());
    }
}