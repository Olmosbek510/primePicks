package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.Order;

import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {
}
