package com.example.group76_part1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    // Set GenerationType to Identity so that SpringBoot automatically instantiates an ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name must not be null or blank")
    private String name;

    @Positive(message = "Budget must be a positive value")
    @NotNull(message = "Budget must not be null")
    private Double budget;

    @NotBlank(message = "Location must not be null or blank")
    private String location;

    // Generating an ArrayList of all Assignments related to this department
    // mappedBy onto department, so we can use department ID as a foreign key in Assignment
    // Initialises an empty array, so that no NullException is thrown upon creation.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }
}
