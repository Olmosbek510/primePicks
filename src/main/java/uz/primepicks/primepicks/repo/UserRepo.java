package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    public User findByUsername(String username);
}
