package com.project.roku.controller;

import com.project.roku.entity.Patient;
import com.project.roku.services.patient_services.PatientRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patients") // this is the URL
public class PatientController {
    private PatientRepoService patientService;

    @Autowired
    public PatientController(PatientRepoService thePatientService){
        this.patientService = thePatientService;
    }

    // get the patient from the database

    @GetMapping("/patientList")
    public String patientList(Model theModel){
        List<Patient> thePatients = patientService.findAll();
            theModel.addAttribute("patient", thePatients);
            return "patients/patient-list";
    }
}
