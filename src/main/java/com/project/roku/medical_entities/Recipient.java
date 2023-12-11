package com.project.roku.medical_entities;

import jakarta.persistence.*;

@Entity
@Table(name="recipient")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prescription_transaction_id")
    private long prescriptionTransactionId;

    @Column(name="recipient_id")
    private int recipientId;

    @Column(name="recipient_type")
    private String recipientType;

    @Column(name="pharmacy_id")
    private int pharmacyId;

    @Column(name="prescription_id")
    private int prescriptionId;

    // constructors

    public Recipient(int recipientId, String recipientType, int pharmacyId, int prescriptionId) {
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.pharmacyId = pharmacyId;
        this.prescriptionId = prescriptionId;
    }


    // getters and setters


    public long getPrescriptionTransactionId() {
        return prescriptionTransactionId;
    }

    public void setPrescriptionTransactionId(long prescriptionTransactionId) {
        this.prescriptionTransactionId = prescriptionTransactionId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

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

    // to string


    @Override
    public String toString() {
        return "Recipient{" +
                "prescriptionTransactionId=" + prescriptionTransactionId +
                ", recipientId=" + recipientId +
                ", recipientType='" + recipientType + '\'' +
                ", pharmacyId=" + pharmacyId +
                ", prescriptionId=" + prescriptionId +
                '}';
    }
}
