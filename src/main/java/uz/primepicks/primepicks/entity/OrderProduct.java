package uz.primepicks.primepicks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderProduct extends BaseEntity{
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private Integer amount;
}
