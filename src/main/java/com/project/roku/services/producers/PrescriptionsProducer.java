package com.project.roku.services.producers;

import com.project.roku.DTO.PrescriptionDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionsProducer {

    // declare instances of Rabbit template and Queue
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    // make constructors
    public PrescriptionsProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;

        // use the Jackson to convert the message
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    // method to send the prescriptions
    public void sendThePrescription(PrescriptionDTO prescriptionDto){
        rabbitTemplate.convertAndSend(queue.getName(),prescriptionDto);
    }

}
