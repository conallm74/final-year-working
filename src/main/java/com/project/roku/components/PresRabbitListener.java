package com.project.roku.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.roku.DTO.PrescriptionDTO;
import com.proto.prescription.PharmacyPrescriptionResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PresRabbitListener {

    private final PharmacyPrescriptionServiceImpl pharmacyPrescriptionService;



    @Autowired
    private final PharmacyPrescriptionService pharmacyPrescriptionService;

    /*
    @Component
public class PresRabbitListener {

    private final PharmacyPrescriptionService pharmacyPrescriptionService;

    public PresRabbitListener(PharmacyPrescriptionService pharmacyPrescriptionService) {
        this.pharmacyPrescriptionService = pharmacyPrescriptionService;
    }

    @RabbitListener(queues = "prescriptions-queue")
    public void processPrescriptionDTO(PrescriptionDTO prescriptionDTO) {
        // Process the received prescriptionDTO
        // You can now call your gRPC service to handle this DTO
        pharmacyPrescriptionService.processPrescriptionDTO(prescriptionDTO);
    }
}
     */
}

