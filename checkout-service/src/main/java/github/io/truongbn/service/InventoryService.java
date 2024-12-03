package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.inventory.InventoryInformation;
import github.io.truongbn.inventory.InventoryInformationRequest;
import github.io.truongbn.inventory.InventoryServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class InventoryService {
    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryClient;
    public InventoryInformation getInventoryInformation(String itemId) {
        var request = InventoryInformationRequest.newBuilder().setItemId(itemId).build();
        return inventoryClient.getItemInventory(request);
    }
}
