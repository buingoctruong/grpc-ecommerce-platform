package github.io.truongbn.service;

import github.io.truongbn.shipping.ShippingFeeInformationRequest;
import github.io.truongbn.shipping.ShippingFeeInformationResponse;
import github.io.truongbn.shipping.ShippingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ShippingService extends ShippingServiceGrpc.ShippingServiceImplBase {
    @Override
    public void getShippingFee(ShippingFeeInformationRequest request,
            StreamObserver<ShippingFeeInformationResponse> responseObserver) {
        ShippingFeeInformationResponse response = ShippingFeeInformationResponse.newBuilder()
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
