package com.example.group76_part1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// DTO used for creating or updating a Department

public class DepartmentRequest {

    // Name of the department
    // Value must not be blank or null
    @NotBlank(message = "Department name must not be null or blank")
    private String name;

    // Budget for the department
    // Must not be null and it must be a positive value
    @Positive(message = "Budget must be a positive value")
    @NotNull(message = "Budget must not be null")
    private Double budget;

    // Location of the department
    // Must not be null or blank
    @NotBlank(message = "Location must not be null or blank")
    private String location;

    // Getter method that gets the department name and returns the value
    public String getName() {
        return name;
    }

    // Setter method that sets the department name
    public void setName(String name) {
        this.name = name;
    }

    // getter method that gets the department budget and returns the value
    public Double getBudget() {
        return budget;
    }

    // setter method that sets the department budget
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    // Getter method that gets the location of the department and returns the value
    public String getLocation() {
        return location;
    }

    // Setter method that sets the department location
    public void setLocation(String location) {
        this.location = location;
    }
}