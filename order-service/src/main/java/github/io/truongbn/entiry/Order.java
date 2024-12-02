package github.io.truongbn.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {
    @Id
    private String id;
    private String itemId;
    private String userId;
    @Column(name = "purchase_quantities")
    private int purchaseQuantities;
    @Column(name = "order_amount")
    private double orderAmount;
    private Status status;
}
