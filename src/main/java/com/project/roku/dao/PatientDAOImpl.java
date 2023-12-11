package com.project.roku.dao;

import com.project.roku.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class PatientDAOImpl implements PatientDAO{

    // daoImpl to find the patient by first and last name
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Patient findPatientName(String firstName, String lastName) {
        try{
            String query = "SELECT * FROM patients WHERE FirstName = ? AND LastName = ?\n";
            return entityManager.createQuery(query, Patient.class)
                    .setParameter("FirstName", firstName)
                    .setParameter("LastName", lastName)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    /*
     public Patient findById(int theId) {
        Optional<Patient> result = patientRepository.findById(theId);

        Patient thePatient = null;
        if (result.isPresent()){
            thePatient = result.get();
        }
        else {
            throw new RuntimeException("Couldn't find patient by that ID");
        }
        return thePatient;
    }

    I think this might be closer to what we need. Then again I'm not sure... Maybe need to try a few things.
     */
}
