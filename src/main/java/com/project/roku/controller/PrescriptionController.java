package com.project.roku.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.entity.Patient;
import com.project.roku.medical_entities.Pharmacy;
import com.project.roku.medical_entities.Prescription;
import com.project.roku.services.patient_services.PatientRepoService;
import com.project.roku.services.pharmacy_services.PharmaRepoService;
import com.project.roku.services.pharmacy_services.PharmaRepoServiceImpl;
import com.project.roku.services.prescription_services.PrescriptionRepoService;
import com.project.roku.services.producers.PrescriptionsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    // variable for the dto Json
    @Autowired
    private PrescriptionDTO prescriptionJsonConvert;

    // save the new prescription, convert to dto, and send to the Rabbit Queue
    @PostMapping("/prescribePrescription")
    public String prescribePrescription(@ModelAttribute("prescription") Prescription thePrescription, Patient thePatient) throws JsonProcessingException {


        prescriptionService.save(thePrescription);

        // could do some if statement for the if it's send to the pharmacy if (sendTo == pharmacy ){PrescriptionDTO prescriptionDTO = convertToDTO(thePrescription, thePatient);


        if (thePrescription.getPatient() == null) { // test this to see if this check logic is robust enough
            // convert the prescription to the dto
            PrescriptionDTO prescriptionDTO = convertToDTO(thePrescription, thePatient);

            // check is user is authenticated
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {
                return "redirect:/patients/patientList";
            } else {
                return "redirect:login";
            }
        } else {
            return null; // here will be the logic for the qr code
        }

    }

    // Getter for prescriptionJson
    public PrescriptionDTO getPrescriptionJson() {
        return prescriptionJsonConvert;
    }


    // method to convert the prescription to a dto
    private PrescriptionDTO convertToDTO(Prescription thePrescription, Patient thePatient) {
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
        return prescriptionDTO;
    }


}
