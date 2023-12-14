package com.project.roku.controller;

import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.dao.DTORepo;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    // injecting the prescriptions Producer
    private final PrescriptionsProducer prescriptionsProducer;

    private final DTORepo dtoRepo;
    private PatientRepoService patientService;

    // injecting prescription service
    private PrescriptionRepoService prescriptionService;

    private PharmaRepoService pharmacyRepoService;

    // import Rabbit template
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    PharmaRepoServiceImpl serviceImpl;

    @Autowired
    public PrescriptionController(
            PrescriptionsProducer prescriptionsProducer, DTORepo dtoRepo, PatientRepoService patientService,
            PrescriptionRepoService prescriptionService,
            PharmaRepoService pharmacyRepoService
    ) {
        this.prescriptionsProducer = prescriptionsProducer;
        this.dtoRepo = dtoRepo;
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



    // save the new prescription, convert to dto, and send to the Rabbit Queue
    @PostMapping("/prescribePrescription")
    public String prescribePrescription(@ModelAttribute("prescription") Prescription thePrescription, Patient thePatient){

        prescriptionService.save(thePrescription);

        // could do some if statement for the if it's send to the pharmacy if (sendTo == pharmacy ){PrescriptionDTO prescriptionDTO = convertToDTO(thePrescription, thePatient);

        // convert the prescription to the dto
        PrescriptionDTO prescriptionDTO = convertToDTO(thePrescription, thePatient);
        // save it into the crud repo
        dtoRepo.save(prescriptionDTO);

        // Send the prescription DTO to the RabbitMQ queue
        rabbitTemplate.convertAndSend("prescriptions-queue", prescriptionDTO);


        return "redirect:patients/patient-list";
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
