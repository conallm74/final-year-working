package com.project.roku.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.roku.entity.Patient;
import com.project.roku.medical_entities.Prescription;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.time.LocalDate;

// creating a DTO for data transfer and for easier data entry


public class PrescriptionDTO {


    @JsonProperty("pharmacy_id")
    public int pharmacyId;
    // fields from Prescription

    @JsonProperty("prescription_id")
    private int prescriptionId;

    @JsonProperty("patient_id")
    private int patientId;

    @JsonProperty("medication_name")
    private String medicationName;

    @JsonProperty("prescription_date")
    private java.sql.Date prescriptionDate;

    @JsonProperty("prescribing_doctor")
    private String prescribingDoctor;

    @JsonProperty("dosage")
    private String dosage;

    // fields from Patient

    @JsonProperty("patient_first_name")
    private String patientFirstName;

    @JsonProperty("patient_last_name")
    private String patientLastName;

    @JsonProperty("patient_address")
    private String patientAddress;


    public PrescriptionDTO(){}
    // Create a PrescriptionDTO and populate it with data from the entities
    public void populateFromEntities(Prescription thePrescription, Patient thePatient) {
        this.pharmacyId = thePrescription.getPharmacy().getPharmacyId();
        this.patientFirstName = thePatient.getPatientFirstName();
        this.patientLastName = thePatient.getPatientLastName();
        this.patientAddress = thePatient.getAddress();
        this.patientId = thePatient.getPatientId();
        this.prescriptionId = thePrescription.getPrescriptionId();
        this.prescriptionDate = thePrescription.getPrescriptionDate();
        this.medicationName = thePrescription.getMedicationName();
        this.dosage = thePrescription.getDosage();
        this.prescribingDoctor = thePrescription.getPrescribingDoctor();
    }

    // getters and setters from prescription


    public int getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(int pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }

    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    // getters and setter from patient

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    // to string

    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                ", pharmacyId=" + pharmacyId +
                ", prescriptionId=" + prescriptionId +
                ", patientId=" + patientId +
                ", medicationName='" + medicationName + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                ", prescribingDoctor='" + prescribingDoctor + '\'' +
                ", dosage='" + dosage + '\'' +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                '}';
    }

}
