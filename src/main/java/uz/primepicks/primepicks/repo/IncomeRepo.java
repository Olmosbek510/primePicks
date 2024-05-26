package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeRepo extends JpaRepository<Income, UUID> {
    List<Income> findAllByProductId(UUID productId);
}
