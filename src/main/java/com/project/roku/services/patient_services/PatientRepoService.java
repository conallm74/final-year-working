package com.project.roku.services.patient_services;

import com.project.roku.entity.Employee;
import com.project.roku.entity.Patient;

import java.util.List;

public interface PatientRepoService {
    List<Patient> findAll();

    Patient findById(int theId);

    Patient save(Patient thePatient);

    void deleteById(int theId);
}
