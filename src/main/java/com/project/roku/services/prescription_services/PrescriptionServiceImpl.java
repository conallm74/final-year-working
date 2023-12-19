package com.project.roku.services.prescription_services;

import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.dao.PrescriptionRepo;
import com.project.roku.medical_entities.Prescription;
//import com.proto.prescription.PharmacyPrescriptionRequest;
//import com.proto.prescription.PharmacyPrescriptionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("uniquePrescriptionService")
public class PrescriptionServiceImpl implements PrescriptionRepoService {

    @Autowired
    private PrescriptionRepo prescriptionRepo;

    // model mapper instance
    private ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepo thePrescriptionRepo){
        prescriptionRepo = thePrescriptionRepo;
    }


    @Override
    public List<Prescription> findAll() {
        return prescriptionRepo.findAll();
    }

    @Override
    public Prescription findById(int theId) {
        Optional<Prescription> result = prescriptionRepo.findById(theId);

        Prescription thePrescription = null;
        if (result.isPresent()){
            thePrescription = result.get();
        }
        else {
            throw new RuntimeException("Couldn't find prescription by that prescription by Id" + theId );
        }
        return thePrescription;
    }

    @Override
    public Prescription save(Prescription thePrescription) {
        return prescriptionRepo.save(thePrescription);
    }

    @Override
    public void deleteById(int theId) {
        prescriptionRepo.deleteById(theId);
    }


    // mapping for DTO

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepo prescriptionRepo, ModelMapper theModelMapper){
        this.prescriptionRepo = prescriptionRepo;
        this.modelMapper = theModelMapper;
    }

    public PrescriptionDTO convertPrescriptionToDTO(Prescription prescription) {
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }

    public Prescription convertDTOToPrescription(PrescriptionDTO dto) {
        return modelMapper.map(dto, Prescription.class);
    }


    // mapping the proto file request PharmacyPresRequest to the dto.
/*
    public PharmacyPrescriptionResponse convertDTOToRequest(PrescriptionDTO prescriptionDTO) {

        PharmacyPrescriptionResponse request = PharmacyPrescriptionResponse.newBuilder()
                .setPatientFirstName(prescriptionDTO.getPatientFirstName())
                .setPatientLastName(prescriptionDTO.getPatientLastName())
                .setPatientAddress(prescriptionDTO.getPatientAddress())
                .setPrescriberName(prescriptionDTO.getPrescribingDoctor())
                .setPrescriptionId(prescriptionDTO.getPrescriptionId())
                .setMedicationName(prescriptionDTO.getMedicationName())
                .setDosage(prescriptionDTO.getDosage())
                .setPharmacyId(prescriptionDTO.getPharmacyId())
                .build();
        return request;
    }

 */


}
