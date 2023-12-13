package rokuserver;



import com.project.roku.DTO.PrescriptionDTO;
import com.proto.prescription.PharmacyPrescriptionRequest;
import com.proto.prescription.PharmacyPrescriptionResponse;
import com.proto.prescription.PrescriptionServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PharmacyPrescriptionService extends PrescriptionServiceGrpc.PrescriptionServiceImplBase {


    @Override
    public StreamObserver<PharmacyPrescriptionRequest> serverStreamingRPC(StreamObserver<PharmacyPrescriptionResponse> responseObserver) {

        return new StreamObserver<PharmacyPrescriptionRequest>() {

            @Override
            public void onNext(PharmacyPrescriptionRequest request) {

                int pharmacyId = request.getPharmacyId();

                // create
                PrescriptionDTO prescriptionDTO = getPrescriptionDTO(request.getPharmacyId());

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
                  string patient_last_name = 2;
  string patient_address = 3;
  string prescriber_name = 4;
  int32 prescription_id = 5;
  string medication_name = 6;
  google.protobuf.Timestamp prescription_date = 7;
  string dosage = 8;
  Target target_type = 9;
  int32 pharmacy_id = 10;
                 */

               //  String dosage = request.getDosage();
               //  int prescriptionId = request.getPrescriptionId();

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

    /*
    @Service
public class PharmacyPrescriptionServiceImpl extends PharmacyPrescriptionServiceGrpc.PharmacyPrescriptionServiceImplBase {

    @Override
    public void sendPrescription(PharmacyPrescriptionRequest request, StreamObserver<PharmacyPrescriptionResponse> responseObserver) {
        // Implement logic to process the received prescription data
        String prescriptionData = request.getPrescriptionData();
        System.out.println("Received prescription data from doctor: " + prescriptionData);

        // Process the prescription data as needed

        // Send a response back to the doctor
        PharmacyPrescriptionResponse response = PharmacyPrescriptionResponse.newBuilder()
                .setMessage("Prescription received and processed")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
     */




}
