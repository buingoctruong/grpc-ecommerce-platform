package github.io.truongbn.service;

import org.springframework.stereotype.Service;

import github.io.truongbn.item.ItemInformation;
import github.io.truongbn.shipping.Address;
import github.io.truongbn.shipping.ShippingFeeInformationRequest;
import github.io.truongbn.shipping.ShippingFeeInformationResponse;
import github.io.truongbn.shipping.ShippingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class ShippingService {
    @GrpcClient("shipping-service")
    private ShippingServiceGrpc.ShippingServiceBlockingStub shippingClient;
    public ShippingFeeInformationResponse getShippingFee(ItemInformation item, int orderPurchase,
            Address shippingAddress) {
        var request = ShippingFeeInformationRequest.newBuilder().setItem(item)
                .setQuantities(orderPurchase).setShippingAddress(shippingAddress).build();
        return shippingClient.getShippingFee(request);
    }
}
