package in.neelesh.Email.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.neelesh.Email.Entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String>{

}
