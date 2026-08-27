package com.miguelmendezz.mise.repository;

import com.miguelmendezz.mise.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
