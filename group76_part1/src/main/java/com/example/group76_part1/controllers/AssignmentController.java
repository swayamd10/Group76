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
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody AssignmentRequest request) {
        AssignmentResponse response = assignmentService.createAssignment(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}