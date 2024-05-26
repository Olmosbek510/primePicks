package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findOrderProductByProductId(UUID uuid);
}
