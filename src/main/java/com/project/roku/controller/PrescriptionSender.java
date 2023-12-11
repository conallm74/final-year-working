package com.project.roku.controller;

import com.project.roku.medical_entities.Prescription;

public class PrescriptionSender {
    public void sendPrescription(Prescription prescription) {
        // Create a PharmacyPrescriptionReceiver and pass the prescription to it
        PharmacyPrescriptionReceiver receiver = new PharmacyPrescriptionReceiver();
        receiver.processPrescription(prescription);
    }
}