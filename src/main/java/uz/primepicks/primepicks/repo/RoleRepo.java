package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.primepicks.primepicks.entity.Role;
import uz.primepicks.primepicks.entity.enums.RoleName;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByName(RoleName name);
}
