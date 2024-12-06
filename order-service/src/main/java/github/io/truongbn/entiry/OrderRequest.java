package github.io.truongbn.entiry;

import lombok.Value;

@Value
public class OrderRequest {
    private String itemId;
    private String userId;
    private int purchaseQuantities;
    private double orderAmount;
}
