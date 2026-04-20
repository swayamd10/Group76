package com.example.group76_part1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    // Set GenerationType to Identity so that SpringBoot automatically instantiates an ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name must not be null or blank")
    private String name;

    @NotBlank(message = "Contract type must not be null or blank")
    private String contractType;

    @NotNull(message = "Start date must not be a null value")
    private LocalDate startDate;

    @Positive(message = "Salary must be a positive value")
    @NotNull(message = "Salary must not be a null value")
    private Double salary;

    @Email(message = "Email must be a valid email address")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Rank must not be blank")
    @Column(name = "employee_rank")
    private String rank;

    // Generating an ArrayList of all Assignments related to this department
    // mappedBy onto employee, so we can use employee ID as a foreign key in Assignment
    // Initialises an empty array, so that no NullException is thrown upon creation.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContractType() { return contractType; }
    public void setContractType(String contractType) { this.contractType = contractType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }
}
