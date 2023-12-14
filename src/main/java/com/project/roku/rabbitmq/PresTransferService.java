package com.project.roku.rabbitmq;

import com.project.roku.DTO.PrescriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class PresTransferService {

    @Autowired
    private PresRabbitListener presRabbitListener;

    public PrescriptionDTO getProcessedPrescription() throws InterruptedException {
        // wait until the prescription data is available
        while (!presRabbitListener.isProcessedPresAvailable()){
            Thread.sleep(100);
        }
        // Retrieve the data
        return presRabbitListener.getProcessedPrescription();

    }
}
