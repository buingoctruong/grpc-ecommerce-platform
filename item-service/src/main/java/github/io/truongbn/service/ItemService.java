package github.io.truongbn.service;

import github.io.truongbn.item.ItemInformation;
import github.io.truongbn.item.ItemInformationRequest;
import github.io.truongbn.item.ItemServiceGrpc;
import github.io.truongbn.item.ItemSizeInformation;
import github.io.truongbn.repository.ItemRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ItemService extends ItemServiceGrpc.ItemServiceImplBase {
    private final ItemRepository itemRepository;
    @Override
    public void getItemInformation(ItemInformationRequest request,
            StreamObserver<ItemInformation> responseObserver) {
        var item = itemRepository.findById(request.getItemId()).orElseThrow();
        var itemInformation = ItemInformation.newBuilder().setItemId(item.getId())
                .setItemName(item.getName()).setPrice(item.getPrice())
                .setSize(ItemSizeInformation.newBuilder().setWidth(item.getWidth())
                        .setHeight(item.getHeight()).setDepth(item.getDepth()).build())
                .build();
        responseObserver.onNext(itemInformation);
        responseObserver.onCompleted();
    }
}
