package com.project.roku.dao;

import com.project.roku.entity.Patient;

public interface PatientDAO {

    Patient findPatientName(String firstName, String lastName);

}
