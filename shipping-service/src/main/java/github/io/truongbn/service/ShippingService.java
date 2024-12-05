package github.io.truongbn.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import github.io.truongbn.item.ItemInformation;
import github.io.truongbn.item.ItemSizeInformation;
import github.io.truongbn.shipping.Address;
import github.io.truongbn.shipping.ShippingFeeInformationRequest;
import github.io.truongbn.shipping.ShippingFeeInformationResponse;
import github.io.truongbn.shipping.ShippingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ShippingService extends ShippingServiceGrpc.ShippingServiceImplBase {
    private static final double BASE_FEE = 5.0;
    private static final double PRICE_DISCOUNT_THRESHOLD = 100.0;
    private static final double PRICE_DISCOUNT = 0.1;
    private static final double REMOTE_AREA_SURCHARGE = 10.0;
    private static final double HIGH_DEMAND_CITY_SURCHARGE = 5.0;
    // List of Remote Areas in Japan
    private static final Set<String> REMOTE_AREAS = new HashSet<>(Arrays.asList("Miyakojima",
            "Ishigaki", "Taketomi", "Tarama", "Rebun Island", "Rishiri Island", "Shikotan Island",
            "Nemuro Peninsula", "Yakushima", "Amami Oshima", "Tokunoshima", "Shodoshima",
            "Naoshima", "Teshima", "Ogasawara Islands", "Izu Oshima", "Hachijojima"));
    // List of High-Demand Cities in Japan
    private static final Set<String> HIGH_DEMAND_CITIES = new HashSet<>(
            Arrays.asList("Tokyo", "Osaka", "Yokohama", "Nagoya", "Fukuoka", "Sapporo", "Kyoto",
                    "Kobe", "Hiroshima", "Sendai"));
    @Override
    public void getShippingFee(ShippingFeeInformationRequest request,
            StreamObserver<ShippingFeeInformationResponse> responseObserver) {
        ShippingFeeInformationResponse response = ShippingFeeInformationResponse.newBuilder()
                .setFee(calculateShippingFee(request)).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public double calculateShippingFee(ShippingFeeInformationRequest request) {
        ItemInformation item = request.getItem();
        ItemSizeInformation size = item.getSize();
        int quantities = request.getQuantities();
        Address address = request.getShippingAddress();
        // Calculate total order price
        double totalPrice = item.getPrice() * quantities;
        // Calculate size-based multiplier (volume)
        double itemVolume = size.getWidth() * size.getDepth() * size.getHeight();
        // Normalize volume
        double sizeMultiplier = Math.max(1.0, itemVolume / 1000.0);
        // Base shipping cost based on size and quantity
        double shippingFee = BASE_FEE + (sizeMultiplier * quantities);
        // Apply discount if total price exceeds threshold
        if (totalPrice >= PRICE_DISCOUNT_THRESHOLD) {
            shippingFee *= (1 - PRICE_DISCOUNT);
        }
        // Add address-based surcharges
        if (REMOTE_AREAS.contains(address.getCity())) {
            shippingFee += REMOTE_AREA_SURCHARGE;
        } else if (HIGH_DEMAND_CITIES.contains(address.getCity())) {
            shippingFee += HIGH_DEMAND_CITY_SURCHARGE;
        }
        // Ensure non-negative fee and return rounded value
        return Math.max(0, Math.round(shippingFee * 100.0) / 100.0);
    }
}
