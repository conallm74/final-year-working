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




            @Override
                 */

                int pharmacyId = request.getPharmacyId();



                /*
                 // Use the populated PrescriptionDTO from the controller
        PrescriptionDTO prescriptionDTO = getPrescriptionDTO(request.getPharmacyId());


              // Create a PharmacyPrescriptionResponse message and populate it with data from the DTO
        PharmacyPrescriptionResponse.Builder responseBuilder = PharmacyPrescriptionResponse.newBuilder()
            .setPatientFirstName(prescriptionDTO.getPatientFirstName())
            .setPatientLastName(prescriptionDTO.getPatientLastName())
            .setPatientAddress(prescriptionDTO.getPatientAddress())
            .setPrescriberName(prescriptionDTO.getPrescriberName())
            .setPrescriptionId(prescriptionDTO.getPrescriptionId())
            .setMedicationName(prescriptionDTO.getMedicationName())
            .setPrescriptionDate(Timestamp.newBuilder().setSeconds(prescriptionDTO.getPrescriptionDate().getTime() / 1000).build())
            .setDosage(prescriptionDTO.getDosage())
            .setTargetType(Target.PHARMACY)
            .setPharmacyId(prescriptionDTO.getPharmacyId());

        // Send the response to the client
        responseObserver.onNext(responseBuilder.build());;



                 */

                // prescriptionDTO.setPatientFirstName(request.getPatientFirstName());


/*
                String patientFirstName = request.getPatientFirstName();
                String patientLastName = request.getPatientLastName();
                String patientAddress = request.getPatientAddress();
                String prescriberName = request.getPrescriberName();
                String medicationName = request.getMedicationName();

 */
/*
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPrescriptionId(request.getPrescriptionId()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientFirstName(request.getPatientFirstName()).build());;
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientLastName(request.getPatientLastName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientAddress(request.getPatientAddress()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPrescriberName(request.getPrescriberName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setMedicationName(request.getMedicationName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setDosage(request.getDosage()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPharmacyId(request.getPharmacyId()).build());

 */
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
