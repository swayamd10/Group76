package com.example.group76_part1.controllers;

import com.example.group76_part1.dtos.EmployeeRequest;
import com.example.group76_part1.dtos.EmployeeResponse;
import com.example.group76_part1.dtos.PromotionResponse;
import com.example.group76_part1.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_HR')")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestParam(required = false) String department) {
        return ResponseEntity.ok(employeeService.getAllEmployees(department));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_HR')")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/promote")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<PromotionResponse> promoteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.promoteEmployee(id));
    }

}
