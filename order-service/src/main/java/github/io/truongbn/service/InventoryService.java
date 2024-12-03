package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.inventory.InventoryServiceGrpc;
import github.io.truongbn.inventory.InventorySyncRequest;
import github.io.truongbn.inventory.InventorySyncResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class InventoryService {
    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryClient;
    public InventorySyncResponse syncInventory(String itemId, int purchaseQuantities) {
        var request = InventorySyncRequest.newBuilder().setItemId(itemId)
                .setPurchaseQuantities(purchaseQuantities).build();
        return inventoryClient.syncInventory(request);
    }
}
