package com.project.roku.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.roku.DTO.PrescriptionDTO;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class PresRabbitListener {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private volatile PrescriptionDTO processedPrescription;

    @Autowired
    public PresRabbitListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "prescriptions-queue")
    public void prescriptionsReceiver(PrescriptionDTO prescriptionDTO) throws JsonProcessingException {

            // convert the dto to JSON format
            String prescriptionJson = objectMapper.writeValueAsString(prescriptionDTO);

            // send the JSON message to the prescription-exchange queue
            rabbitTemplate.convertAndSend("prescription-exchange", "prescriptions", prescriptionJson);

            // make reference for the processed prescription that's available
            processedPrescription = prescriptionDTO;




    }// listener

    boolean isProcessedPresAvailable() {
        return processedPrescription != null;
    }

    public PrescriptionDTO getProcessedPrescription(){
        return processedPrescription;
    }

} // class


