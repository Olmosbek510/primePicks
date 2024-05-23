package uz.primepicks.primepicks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentContent extends BaseEntity{
    private byte[] content;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Attachment attachment;
}
