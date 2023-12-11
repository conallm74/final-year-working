package com.project.roku.services.prescription_services;

import com.project.roku.entity.Employee;
import com.project.roku.medical_entities.Prescription;

import java.util.List;

public interface PrescriptionRepoService {

    List<Prescription> findAll();

    Prescription findById(int theId);

    Prescription save(Prescription thePrescription);

    void deleteById(int theId);


}
