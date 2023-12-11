package client_service.service;

import com.proto.prescription.PrescriptionServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class PharmaPresService {



    @GrpcClient("grpc-pharmacy-service")
    PrescriptionServiceGrpc.PrescriptionServiceStub clientStub;

    // what do we do need to do here? In the Udemy course he ran the

    // so we're going to need to get the pharmacy identifier

    // then implement the logic for overriding the onNext method that will get the prescription

    // will have to manage the dependencies


    // and we're also going to have to get the prescription. And for that, we may need to get a controller?

    // if we wanted to pass one object from one controller to another controller, what would be the point of the proto?




    /*
    import io.grpc.stub.StreamObserver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ClientPharmaPresService {

    @GrpcClient("grpc-callprescription-service")
    PrescriptionServiceGrpc.PrescriptionServiceStub prescriptionServiceStub;

    // Replace this variable with the actual pharmacy identifier
    private String pharmacyIdentifier = "pharmacy123";

    private StreamObserver<PharmacyPrescriptionRequest> requestObserver;

    public void startStreaming() {
        StreamObserver<PharmacyPrescriptionResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(PharmacyPrescriptionResponse response) {
                // Handle the prescription update received from the server
                // You can implement your logic here
                System.out.println("Received Prescription Update: " + response);
            }

            @Override
            public void onError(Throwable throwable) {
                // Handle errors
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Handle completion (optional)
            }
        };

        // Initialize the requestObserver
        requestObserver = prescriptionServiceStub.serverStreamingRPC(responseObserver);

        // Send the initial request with the pharmacy identifier
        PharmacyPrescriptionRequest initialRequest = PharmacyPrescriptionRequest.newBuilder()
                .setPharmacyIdentifier(pharmacyIdentifier)
                .build();
        requestObserver.onNext(initialRequest);
    }

    @Scheduled(fixedDelay = 1000) // Adjust the delay based on your needs
    public void sendUpdateRequest() {
        // Send a request for updates (empty request, as we are continuously polling)
        PharmacyPrescriptionRequest updateRequest = PharmacyPrescriptionRequest.newBuilder()
                .setPharmacyIdentifier(pharmacyIdentifier)
                .build();
        requestObserver.onNext(updateRequest);
    }
}

     */


}
