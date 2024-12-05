package github.io.truongbn.model;

import lombok.Value;

@Value
public class CheckoutRequest {
    private String itemId;
    private String userId;
    private int purchaseQuantities;
    private ShippingAddress shippingAddress;
    public record ShippingAddress(String postalCode, String city, String state, String street,
            String buildingName, String roomNumber) {
    }
}
