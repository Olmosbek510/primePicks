package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.projection.ProductProjection;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    @Query("select p from Product  p order by p.name")
    List<Product> findAllOrderByName();
    List<Product> findALlByCategoryIdOrderByName(UUID categoryId);
    @Query("select t.id as id, t.name as name, t.retailPrice as price, a.content as photo from Product t join AttachmentContent a on t.photo.id = a.attachment.id where t.id = :uuid")
    ProductProjection findProductProjById(UUID uuid);

    @Query(nativeQuery = true, value = """
                select (sum(i.amount) -
                        (select sum(o.amount) from order_product o where o.product_id = :id))
                from income i
                where i.product_id = :id
            """)
    Integer getRemainingById(UUID id);
}
