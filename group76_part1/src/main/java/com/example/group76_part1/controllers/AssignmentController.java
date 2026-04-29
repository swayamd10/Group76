package com.example.group76_part1.controllers;

import com.example.group76_part1.dtos.AssignmentRequest;
import com.example.group76_part1.dtos.AssignmentResponse;
import com.example.group76_part1.services.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/assignments") // Sets the URL mapping for all endpoints in this controller
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // This is used to create a new assignment
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_HR')") // only allows access to users who have the ROLE_HR to create assignments
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody AssignmentRequest request) {
        // Service returns a response containing the created assignment data
        AssignmentResponse response = assignmentService.createAssignment(request);

        // Builds the URI of the newly created assignment
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    // This is used to delete an assignment by its ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_HR')") // Only HR users are allowed to delete assignments
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id); // calls the service layer and deletes the assignment with the given ID
        return ResponseEntity.noContent().build();
    }
}