package in.neelesh.Email.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.neelesh.Email.Entity.Email;
import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Enum.EmailStatus;

@Repository
public interface EmailRepository extends JpaRepository<Email, String>{

	List<Email> findByReceiverAndStatus(User receiver, EmailStatus status);
    List<Email> findBySender(User sender);
}
