package com.example.group76_part1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DepartmentRequest {

    @NotBlank(message = "Department name must not be null or blank")
    private String name;

    @Positive(message = "Budget must be a positive value")
    @NotNull(message = "Budget must not be null")
    private Double budget;

    @NotBlank(message = "Location must not be null or blank")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}