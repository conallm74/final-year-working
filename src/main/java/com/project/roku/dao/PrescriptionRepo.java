package com.project.roku.dao;

import com.project.roku.entity.Patient;
import com.project.roku.medical_entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepo extends JpaRepository<Prescription, Integer>{
    // methods findAll, findById, save, and deleteById are included automatically by JpaRepo
}
