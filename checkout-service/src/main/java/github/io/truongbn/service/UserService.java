package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.user.UserInformation;
import github.io.truongbn.user.UserInformationRequest;
import github.io.truongbn.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class UserService {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;
    public UserInformation getUserInformation(String userId) {
        var request = UserInformationRequest.newBuilder().setUserId(userId).build();
        return userClient.getUserInformation(request);
    }
}
