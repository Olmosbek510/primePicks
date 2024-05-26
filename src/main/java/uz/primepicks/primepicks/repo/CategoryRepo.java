package uz.primepicks.primepicks.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.primepicks.primepicks.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    @Query("select c from Category  c order by c.name")
    List<Category> findAllOrderByName();

    List<Category> findAllByNameContainingIgnoreCase(String name);
}
