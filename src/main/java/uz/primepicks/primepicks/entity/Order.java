package uz.primepicks.primepicks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {
    @ManyToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime time;
}
