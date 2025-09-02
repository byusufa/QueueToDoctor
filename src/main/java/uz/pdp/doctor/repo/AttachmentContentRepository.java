package uz.pdp.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.doctor.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
}