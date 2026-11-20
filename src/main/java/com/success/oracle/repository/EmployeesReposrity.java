package com.success.oracle.repository;


import com.success.oracle.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesReposrity extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    @Query("from Employee e")
    public List<Employee> getEmployees();
}
