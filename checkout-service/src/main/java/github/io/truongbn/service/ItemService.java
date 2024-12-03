package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.item.ItemInformation;
import github.io.truongbn.item.ItemInformationRequest;
import github.io.truongbn.item.ItemServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class ItemService {
    @GrpcClient("item-service")
    private ItemServiceGrpc.ItemServiceBlockingStub itemClient;
    public ItemInformation getItemInformation(String itemId) {
        var request = ItemInformationRequest.newBuilder().setItemId(itemId).build();
        return itemClient.getItemInformation(request);
    }
}
