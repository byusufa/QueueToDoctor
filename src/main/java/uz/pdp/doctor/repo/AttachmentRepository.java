package uz.pdp.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.doctor.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}