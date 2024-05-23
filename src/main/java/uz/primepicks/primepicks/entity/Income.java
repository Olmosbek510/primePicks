package uz.primepicks.primepicks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income extends BaseEntity {
    @OneToOne
    private Product product;
    private Integer amount;
    private Integer wholeSalePrice;
}
