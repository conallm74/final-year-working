package com.project.roku.grpc_server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile("grpc-server")
public class ServerConfig {



/*

    @Value("${grpc.server.port}")
    private int port;


    private Server server;


    int port = 50051;
    //auto wire the service impl
    @Autowired
    private com.project.roku.grpc_server.PharmacyPrescriptionService pharmacyPrescriptionService;

    @PostConstruct
    public void startGrpcServer() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(port)
                .addService(pharmacyPrescriptionService)
                .build()
                .start();


        System.out.println("Server started");
        System.out.println("LISTENING on port:" + port);

        // shut down of the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
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
    @ConditionalOnProperty(name = "grpc.server.enable", havingValue = "true")
    public Server grpcServer() {
        return server;
    }

 */



}