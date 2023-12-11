package com.project.roku.controller;

import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.entity.Patient;
import com.project.roku.medical_entities.Pharmacy;
import com.project.roku.medical_entities.Prescription;
import com.project.roku.services.patient_services.PatientRepoService;
import com.project.roku.services.pharmacy_services.PharmaRepoService;
import com.project.roku.services.pharmacy_services.PharmaRepoServiceImpl;
import com.project.roku.services.prescription_services.PrescriptionRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {


    private PatientRepoService patientService;

    // injecting prescription service
    private PrescriptionRepoService prescriptionService;

    private PharmaRepoService pharmacyRepoService;

    @Autowired
    PharmaRepoServiceImpl serviceImpl;

    @Autowired
    public PrescriptionController(
            PatientRepoService patientService,
            PrescriptionRepoService prescriptionService,
            PharmaRepoService pharmacyRepoService
    ) {
        this.pharmacyRepoService = pharmacyRepoService;
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
    }


    // show the form with the pre-populated patient information.
    @GetMapping("/showPresForm")
    public String showPresForm(@RequestParam(value = "patientId") int theId, Model theModel) {
        // find the patient by the Id.
        Patient thePatient = patientService.findById(theId);

        // add the patient to the model
        theModel.addAttribute("patient", thePatient);

        // add the prescription
        Prescription thePrescription = new Prescription();
        theModel.addAttribute("prescription", thePrescription);

        // retrieving the pharmacies for the drop-down menu
        List<Pharmacy> pharmacyNames = serviceImpl.findAll();
        theModel.addAttribute("pharmacies", pharmacyNames);

        // send over to our form
        return "prescriptions/prescription-form";
    }

    // reference for the sent prescription

    Prescription theSentPrescription;

    // save the new prescription
    @PostMapping("/prescribePrescription")
    public String prescribePrescription(@ModelAttribute("prescription") Prescription thePrescription){

        prescriptionService.save(thePrescription);

        PrescriptionSender sender = new PrescriptionSender();
        sender.sendPrescription(thePrescription);


        theSentPrescription = thePrescription;
        return "redirect:patients/patient-list";
    }

    @PostMapping("/sendPrescription")
    public String sendPrescription(@ModelAttribute("prescription") Prescription thePrescription,
                                   @ModelAttribute("patient") Patient thePatient,
                                   Model theModel) {
        // create DTO for gRPC
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        // map related fields
        prescriptionDTO.setPatientFirstName(thePatient.getPatientFirstName());
        prescriptionDTO.setPatientLastName(thePatient.getPatientLastName());
        prescriptionDTO.setDosage(thePrescription.getDosage());
        prescriptionDTO.setPrescriptionDate(thePrescription.getPrescriptionDate());
        prescriptionDTO.setPrescribingDoctor(thePrescription.getPrescribingDoctor());
        prescriptionDTO.setPrescriptionId(thePrescription.getPrescriptionId());
        prescriptionDTO.setPatientAddress(thePatient.getPatientAddress());
        prescriptionDTO.setMedicationName(thePrescription.getMedicationName());

        // send to server
        // grpcService.sendPrescription(prescriptionDTO);

        return null;
    }

    public Prescription getTheSentPrescription() {
        return theSentPrescription;
    }

    public void setTheSentPrescription(Prescription theSentPrescription) {
        this.theSentPrescription = theSentPrescription;
    }

    @Override
    public String toString() {
        return "PrescriptionController{" +
                "theSentPrescription=" + theSentPrescription +
                '}';
    }
}
