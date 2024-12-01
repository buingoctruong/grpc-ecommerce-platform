package github.io.truongbn.service;

import github.io.truongbn.repository.UserRepository;
import github.io.truongbn.shipping.Address;
import github.io.truongbn.user.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository userRepository;
    @Override
    public void getUserInformation(UserInformationRequest request,
            StreamObserver<UserInformation> responseObserver) {
        var user = userRepository.findById(request.getUserId()).orElseThrow();
        var userInformation = UserInformation.newBuilder().setUserId(user.getId())
                .setUserName(user.getName()).setBalance(user.getBalance())
                .setShippingAddress(Address.newBuilder().setPostalCode(user.getPostalCode())
                        .setCity(user.getCity()).setState(user.getState())
                        .setStreet(user.getStreet()).setBuildingName(user.getBuildingName())
                        .setRoomNumber(user.getRoomNumber()).build())
                .build();
        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void executeUserDebitBalance(UserDebitBalanceRequest request,
            StreamObserver<UserDebitBalanceResponse> responseObserver) {
        var res = userRepository.updateUserBalanceByUserId(request.getUserId(),
                request.getNewBalance());
        var syncResponse = UserDebitBalanceResponse.newBuilder().setResult(res == 1).build();
        responseObserver.onNext(syncResponse);
        responseObserver.onCompleted();
    }
}
