package com.project.roku.medical_entities;

import com.project.roku.entity.Patient;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prescription_id")
    private int prescriptionId;

    @Column(name="patient_id")
    private int patientId;

    @Column(name="medication_name")
    private String medicationName;

    @Column(name="prescription_date")
    private java.sql.Date prescriptionDate;

    @Column(name="dosage")
    private String dosage;

    @Column(name="prescribing_doctor")
    private String prescribingDoctor;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "sent_to_pharmacy")
    private Pharmacy pharmacy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "sent_to_patient")
    private Patient patient;

    // constructors
    public Prescription(){}

    public Prescription(int prescriptionId, int patientId, String medicationName, Date prescriptionDate, String dosage, String prescribingDoctor, Pharmacy pharmacy, Patient patient) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.medicationName = medicationName;
        this.prescriptionDate = prescriptionDate;
        this.dosage = dosage;
        this.prescribingDoctor = prescribingDoctor;
        this.pharmacy = pharmacy;
        this.patient = patient;
    }


    // getters and setters

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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPrescribingDoctor() {
        return prescribingDoctor;
    }

    public void setPrescribingDoctor(String prescribingDoctor) {
        this.prescribingDoctor = prescribingDoctor;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // to string


    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", patientId=" + patientId +
                ", medicationName='" + medicationName + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                ", dosage='" + dosage + '\'' +
                ", prescribingDoctor='" + prescribingDoctor + '\'' +
                ", pharmacy=" + pharmacy +
                ", patient=" + patient +
                '}';
    }
}
