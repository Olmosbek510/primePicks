package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.Order;
import uz.primepicks.primepicks.projection.OrderProductProjection;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {
    List<Order> findAllByUserId(UUID userId);
}
