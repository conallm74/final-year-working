package com.project.roku.services.employee_services;

import com.project.roku.entity.Employee;

import java.util.List;

public interface EmployeeRepoService {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theUser);

    void deleteById(int theId);
}
