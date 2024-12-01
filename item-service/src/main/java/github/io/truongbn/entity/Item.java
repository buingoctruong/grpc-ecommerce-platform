package github.io.truongbn.entity;

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
public class Item {
    @Id
    private String id;
    private String name;
    private double price;
    private double width;
    private double depth;
    private double height;
}
