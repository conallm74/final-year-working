package com.project.roku.grpc_server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ServerConfig {

    @Value("${grpc.server.port}")
    private int grpcPort;

    private Server server;

    // auto wire the service impl
    @Autowired
    private PharmacyPrescriptionService pharmacyPrescriptionService;

    @PostConstruct
    public void startGrpcServer() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(grpcPort)
                .addService(pharmacyPrescriptionService)
                .build()
                .start();


        System.out.println("Server started");
        System.out.println("LISTENING on port:" + grpcPort);

        // shut down of the server
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("Received shutdown request");
            // then do shut down
            server.shutdown();
            System.out.println("Server has stopped");
        }));
        // now we wait for a shutdown request
        server.awaitTermination();
    }

    // expose the server as a string bean in case we need it somewhere
    @Bean
    public Server grpcServer() {
        return server;
    }

}
