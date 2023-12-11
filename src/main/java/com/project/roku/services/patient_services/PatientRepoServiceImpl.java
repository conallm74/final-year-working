package com.project.roku.services.patient_services;

import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.dao.PatientRepository;
import com.project.roku.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("uniquePatientRepoService")
public class PatientRepoServiceImpl implements PatientRepoService {


    private PatientRepository patientRepository;

    // model mapper instance
    private ModelMapper modelMapper;

    @Autowired
    public PatientRepoServiceImpl(PatientRepository thePatientRepository){
        patientRepository = thePatientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
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


    @Override
    public Patient save(Patient thePatient) {
        return patientRepository.save(thePatient);
    }


    @Override
    public void deleteById(int theId) {
        patientRepository.deleteById(theId);
    }

    // mapping for DTO
    public PatientRepoServiceImpl(PatientRepository patientRepository, ModelMapper theModelMapper){
        this.patientRepository = patientRepository;
        this.modelMapper = theModelMapper;
    }
    public PrescriptionDTO convertPatientToDTO(Patient patient){
        return modelMapper.map(patient, PrescriptionDTO.class);
    }
    public Patient concertDTOToPatient(PrescriptionDTO dto){
        return modelMapper.map(dto, Patient.class);
    }
}
