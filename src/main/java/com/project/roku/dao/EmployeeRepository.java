package com.project.roku.dao;

import com.project.roku.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // methods finaAll, findById, save, and deleteById are included automatically by JpaRepo

}
