package com.example.group76_part1.services;

import com.example.group76_part1.dtos.EmployeeRequest;
import com.example.group76_part1.dtos.EmployeeResponse;
import com.example.group76_part1.dtos.PromotionResponse;
import com.example.group76_part1.entities.Employee;
import com.example.group76_part1.exceptions.BusinessRuleException;
import com.example.group76_part1.exceptions.ResourceNotFoundException;
import com.example.group76_part1.repos.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<EmployeeResponse> getAllEmployees(String department) {
        List<Employee> employees = (department == null || department.isBlank())
                ? employeeRepository.findAll()
                : employeeRepository.findByDepartmentName(department);

        return employees.stream().map(this::toResponse).toList();
    }

    @Transactional
    public EmployeeResponse getEmployeeById(Long id) {
        return toResponse(findEmployee(id));
    }

    public Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        map(employee, request);

        if (request.getRank() == null || request.getRank().isBlank()) {
            employee.setRank("JUNIOR");
        } else {
            employee.setRank(request.getRank().toUpperCase());
        }

        return toResponse(employeeRepository.save(employee));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = findEmployee(id);
        map(employee, request);

        if (request.getRank() != null && !request.getRank().isBlank()) {
            employee.setRank(request.getRank().toUpperCase());
        }

        return toResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        Employee employee = findEmployee(id);
        employeeRepository.delete(employee);
    }

    private void map(Employee employee, EmployeeRequest request) {
        employee.setName(request.getName());
        employee.setContractType(request.getContractType());
        employee.setStartDate(request.getStartDate());
        employee.setSalary(request.getSalary());
        employee.setEmail(request.getEmail());
        employee.setAddress(request.getAddress());
    }


    public PromotionResponse promoteEmployee(Long id) {
        Employee employee = findEmployee(id);

        LocalDate eligibilityDate = LocalDate.now().minusMonths(6);
        if (employee.getStartDate().isAfter(eligibilityDate)) {
            throw new BusinessRuleException("Employee is not eligible for promotion yet");
        }

        String currentRank = employee.getRank() == null ? "JUNIOR" : employee.getRank().toUpperCase();
        String newRank;
        double raisePercentage;

        switch (currentRank) {
            case "JUNIOR" -> {
                newRank = "MID";
                raisePercentage = 0.10;
            }
            case "MID" -> {
                newRank = "SENIOR";
                raisePercentage = 0.08;
            }
            case "SENIOR" -> {
                newRank = "LEAD";
                raisePercentage = 0.06;
            }
            case "LEAD" -> {
                newRank = "LEAD";
                raisePercentage = 0.05;
            }
            default -> {
                newRank = "MID";
                raisePercentage = 0.10;
            }
        }

        double oldSalary = employee.getSalary();
        double increaseAmount = oldSalary * raisePercentage;
        double newSalary = oldSalary + increaseAmount;

        employee.setRank(newRank);
        employee.setSalary(newSalary);

        employeeRepository.save(employee);

        return new PromotionResponse(
                employee.getId(),
                employee.getName(),
                currentRank,
                newRank,
                oldSalary,
                newSalary,
                increaseAmount,
                "Employee promoted successfully"
        );
    }


    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getContractType(),
                employee.getStartDate(),
                employee.getSalary(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getRank()
        );
    }
}