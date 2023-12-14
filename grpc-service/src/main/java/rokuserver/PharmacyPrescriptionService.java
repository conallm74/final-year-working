package rokuserver;



import com.google.protobuf.Timestamp;
import com.project.roku.DTO.PrescriptionDTO;
import com.project.roku.rabbitmq.PresRabbitListener;
import com.project.roku.rabbitmq.PresTransferService;
import com.proto.prescription.PharmacyPrescriptionRequest;
import com.proto.prescription.PharmacyPrescriptionResponse;
import com.proto.prescription.PrescriptionServiceGrpc;
import com.proto.prescription.Target;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class PharmacyPrescriptionService extends PrescriptionServiceGrpc.PrescriptionServiceImplBase {

    @Autowired
    private PresTransferService presTransferService;

    @Override
    public StreamObserver<PharmacyPrescriptionRequest> serverStreamingRPC(StreamObserver<PharmacyPrescriptionResponse> responseObserver) {

        return new StreamObserver<PharmacyPrescriptionRequest>() {

            @Override
            public void onNext(PharmacyPrescriptionRequest request) {
                // processing the request and retrieving the data from RabbitMQ
                try {
                    PrescriptionDTO processedPres = presTransferService.getProcessedPrescription();
                    System.out.println("Processed prescription is:" + processedPres.toString());
                    int pharmacyId = request.getPharmacyId();
                    // build the response

                    PharmacyPrescriptionResponse.Builder responseBuilder = PharmacyPrescriptionResponse.newBuilder()

                            .setPatientFirstName(processedPres.getPatientFirstName())
                            .setPatientLastName(processedPres.getPatientLastName())
                            .setPatientAddress(processedPres.getPatientAddress())
                            .setPrescriberName(processedPres.getPrescribingDoctor())
                            .setPrescriptionId(processedPres.getPrescriptionId())
                            .setPharmacyId(processedPres.getPharmacyId())
                            .setMedicationName(processedPres.getMedicationName())
                            .setPrescriptionDate(Timestamp.newBuilder().buildPartial())
                            .setDosage(processedPres.getDosage())
                            .setPharmacyId(processedPres.getPharmacyId());
                            // still need the target
                    responseObserver.onNext(responseBuilder.build());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}
