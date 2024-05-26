package uz.primepicks.primepicks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product extends BaseEntity {
    @Column(unique = true)
    private String name;
    private Integer retailPrice;
    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment photo;
}
