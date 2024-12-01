package github.io.truongbn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    private String id;
    private String name;
    private double balance;
    @Column(name = "postal_code")
    private String postalCode;
    private String city;
    private String state;
    private String street;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "room_number")
    private String roomNumber;
}
