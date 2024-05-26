package uz.primepicks.primepicks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.primepicks.primepicks.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findAttachmentContentByAttachmentId(UUID attachmentId);
}
