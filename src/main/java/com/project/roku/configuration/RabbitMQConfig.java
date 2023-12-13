package com.project.roku.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue prescriptionsQueue(){
        return new Queue("prescriptions-queue", false);
    }

}
