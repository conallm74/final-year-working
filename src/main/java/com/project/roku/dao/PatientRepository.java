package com.project.roku.dao;

import com.project.roku.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // methods finaAll, findById, save, and deleteById are included automatically by JpaRepo
}
