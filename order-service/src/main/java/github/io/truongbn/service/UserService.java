package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.user.UserDebitBalanceRequest;
import github.io.truongbn.user.UserDebitBalanceResponse;
import github.io.truongbn.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class UserService {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;
    public UserDebitBalanceResponse executeUserDebitBalance(String userId, double newBalance) {
        var request = UserDebitBalanceRequest.newBuilder().setUserId(userId)
                .setNewBalance(newBalance).build();
        return userClient.executeUserDebitBalance(request);
    }
}
