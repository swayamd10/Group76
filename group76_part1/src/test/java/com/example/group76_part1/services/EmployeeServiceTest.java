package com.example.group76_part1.services;

import com.example.group76_part1.dtos.EmployeeRequest;
import com.example.group76_part1.dtos.PromotionResponse;
import com.example.group76_part1.entities.Employee;
import com.example.group76_part1.exceptions.BusinessRuleException;
import com.example.group76_part1.exceptions.ResourceNotFoundException;
import com.example.group76_part1.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void promoteEmployee_ShouldIncreaseSalaryAndRank_WhenEmployeeIsEligible() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Alice");
        employee.setRank("JUNIOR");
        employee.setSalary(30000.0);
        employee.setStartDate(LocalDate.now().minusMonths(7));
        employee.setContractType("FULL_TIME");
        employee.setEmail("alice@test.com");
        employee.setAddress("Liverpool");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PromotionResponse response = employeeService.promoteEmployee(1L);

        assertEquals("JUNIOR", response.oldRank());
        assertEquals("MID", response.newRank());
        assertEquals(30000.0, response.oldSalary());
        assertEquals(33000.0, response.newSalary());
        assertEquals(3000.0, response.increaseAmount());

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());

        Employee saved = captor.getValue();
        assertEquals("MID", saved.getRank());
        assertEquals(33000.0, saved.getSalary());
    }

    @Test
    void promoteEmployee_ShouldThrowBusinessRuleException_WhenEmployeeStartedLessThanSixMonthsAgo() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("Bob");
        employee.setRank("JUNIOR");
        employee.setSalary(28000.0);
        employee.setStartDate(LocalDate.now().minusMonths(3));

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));

        BusinessRuleException ex = assertThrows(
                BusinessRuleException.class,
                () -> employeeService.promoteEmployee(2L)
        );

        assertEquals("Employee is not eligible for promotion yet", ex.getMessage());
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void promoteEmployee_ShouldThrowResourceNotFoundException_WhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.promoteEmployee(99L));
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void createEmployee_ShouldDefaultRankToJunior_WhenRankNotProvided() {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Charlie");
        request.setContractType("FULL_TIME");
        request.setStartDate(LocalDate.of(2024, 1, 10));
        request.setSalary(25000.0);
        request.setEmail("charlie@test.com");
        request.setAddress("Manchester");

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee e = invocation.getArgument(0);
            e.setId(10L);
            return e;
        });

        var response = employeeService.createEmployee(request);

        assertEquals("JUNIOR", response.rank());
        assertEquals("Charlie", response.name());
    }
}