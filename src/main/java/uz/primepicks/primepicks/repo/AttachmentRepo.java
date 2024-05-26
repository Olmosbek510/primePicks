package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
