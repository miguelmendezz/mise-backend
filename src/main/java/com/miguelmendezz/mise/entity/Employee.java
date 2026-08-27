package com.miguelmendezz.mise.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    private boolean freelancer;

    public Employee() {
    }

    public Employee(String name, boolean freelancer) {
        this.name = name;
        this.freelancer = freelancer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFreelancer() {
        return freelancer;
    }

    public void setFreelancer(boolean freelancer) {
        this.freelancer = freelancer;
    }
}
