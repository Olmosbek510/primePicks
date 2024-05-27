package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.primepicks.primepicks.entity.OrderProduct;
import uz.primepicks.primepicks.projection.OrderProductProjection;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findOrderProductByProductId(UUID uuid);

    @Query("select a.content as photo, p.name as name, p.retailPrice as price, o.amount as amount from Product p join OrderProduct o on o.product.id = p.id and o.order.id = :orderId join AttachmentContent a on a.attachment.id = p.photo.id")
    List<OrderProductProjection> findAllByOrderId(UUID orderId);
}
