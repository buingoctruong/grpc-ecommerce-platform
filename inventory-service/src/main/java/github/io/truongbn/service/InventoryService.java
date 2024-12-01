package github.io.truongbn.service;

import github.io.truongbn.inventory.*;
import github.io.truongbn.repository.InventoryRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class InventoryService extends InventoryServiceGrpc.InventoryServiceImplBase {
    private final InventoryRepository inventoryRepository;
    @Override
    public void getItemInventory(InventoryInformationRequest request,
            StreamObserver<InventoryInformation> responseObserver) {
        var inventory = inventoryRepository.findByItemId(request.getItemId()).orElseThrow();
        var inventoryInformation = InventoryInformation.newBuilder()
                .setItemId(inventory.getItemId()).setQuantities(inventory.getQuantities()).build();
        responseObserver.onNext(inventoryInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void syncInventory(InventorySyncRequest request,
            StreamObserver<InventorySyncResponse> responseObserver) {
        var res = inventoryRepository.updateQuantitiesByItemId(request.getItemId(),
                request.getPurchaseQuantities());
        var syncResponse = InventorySyncResponse.newBuilder().setResult(res == 1).build();
        responseObserver.onNext(syncResponse);
        responseObserver.onCompleted();
    }
}
