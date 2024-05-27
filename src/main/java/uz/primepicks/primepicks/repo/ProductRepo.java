package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.projection.ProductProjection;
import uz.primepicks.primepicks.projection.ProductProfitProjection;
import uz.primepicks.primepicks.projection.SalesReportProjection;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

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

    @Query(nativeQuery = true, value = """
            select p.id as id, p.name, p.retail_price as price, sum(o.amount) as amount, sum(o.amount) * p.retail_price as total
            from order_product o
                     join public.product p on p.id = o.product_id
            group by p.name, p.retail_price, p.id
            """)
    List<SalesReportProjection> getReport();

    @Query(nativeQuery = true, value = """
            select a.content                                          as photo,
                   p.name                                             as name,
                   p.retail_price                                     as price,
                   sum(o.amount)                                      as amount,
                   sum(o.amount) * p.retail_price                     as totalsales,
                   sum(o.amount) * p.retail_price - (select sum(i.amount * i.whole_sale_price) / sum(i.amount) * :totalSales
                                                     from income i
                                                              join product p on i.product_id = p.id
                                                     where i.product_id = :id
                                                     group by p.name) as profit
            from order_product o
                     join product p on o.product_id = p.id
                     join attachment_content a on a.attachment_id = p.photo_id
            where o.product_id = :id
            group by p.name, p.retail_price, a.content, p.retail_price;
            """)
    ProductProfitProjection getProfitByProductId(UUID id, Integer totalSales);
}
