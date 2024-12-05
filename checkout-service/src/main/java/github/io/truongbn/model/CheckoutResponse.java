package github.io.truongbn.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckoutResponse {
    private String itemId;
    private String userId;
    private int purchaseQuantities;
    private double orderAmount;
}
