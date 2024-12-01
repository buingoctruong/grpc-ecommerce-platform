package github.io.truongbn.entiry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Value;

@Entity
@Table(name = "item")
@Value
@Builder(toBuilder = true)
public class Order {
    @Id
    private String id;
    private String itemId;
    private String userId;
    private int purchaseQuantities;
    private double orderAmount;
    private String status;
}
