package com.project.roku.services.dtorepo_service;

import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.medical_entities.Prescription;

public interface DTORepoService {

    PrescriptionDTO save(PrescriptionDTO thePrescriptionDTO);
}
