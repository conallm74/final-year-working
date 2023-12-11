package rokuserver;

import com.project.roku.controller.PrescriptionController;
import com.project.roku.medical_entities.Prescription;
import com.project.roku.services.prescription_services.PrescriptionServiceImpl;
import com.proto.prescription.PharmacyPrescriptionRequest;
import com.proto.prescription.PharmacyPrescriptionResponse;
import com.proto.prescription.PrescriptionServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class PharmacyPrescriptionService extends PrescriptionServiceGrpc.PrescriptionServiceImplBase {

    // reference for PrescriptionServiceImpl
    private final PrescriptionServiceImpl prescriptionService;

    private final PrescriptionController prescriptionController;

    private final Prescription prescription;

    // injecting the prescription services to make use of their methods
    @Autowired
    public PharmacyPrescriptionService(PrescriptionServiceImpl prescriptionService, PrescriptionController prescriptionController, Prescription prescription) {
        this.prescriptionService = prescriptionService;
        this.prescriptionController = prescriptionController;
        this.prescription = prescription;
    }



    @Override
    public StreamObserver<PharmacyPrescriptionRequest> serverStreamingRPC(StreamObserver<PharmacyPrescriptionResponse> responseObserver) {

        return new StreamObserver<PharmacyPrescriptionRequest>() {

            // get the reference of theSentPrescription ? How?


            @Override
            public void onNext(PharmacyPrescriptionRequest request) {



                PrescriptionController.getTheSentPrescription



                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPrescriptionId(request.getPrescriptionId()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientFirstName(request.getPatientFirstName()).build());;
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientLastName(request.getPatientLastName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPatientAddress(request.getPatientAddress()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPrescriberName(request.getPrescriberName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setMedicationName(request.getMedicationName()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setDosage(request.getDosage()).build());
                responseObserver.onNext(PharmacyPrescriptionResponse.newBuilder().setPharmacyId(request.getPharmacyId()).build());
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
