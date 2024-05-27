package uz.primepicks.primepicks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income extends BaseEntity {
    @ManyToOne
    private Product product;
    private Integer amount;
    private Integer wholeSalePrice;
    @CreationTimestamp
    private LocalDateTime dateTime;
}
