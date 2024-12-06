package github.io.truongbn.entiry;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {
    @Id
    private String id;
    @Column(name = "item_id")
    private String itemId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "purchase_quantities")
    private int purchaseQuantities;
    @Column(name = "order_amount")
    private double orderAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
}
