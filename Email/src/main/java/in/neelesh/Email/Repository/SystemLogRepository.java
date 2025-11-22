package in.neelesh.Email.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.neelesh.Email.Entity.SystemLog;
import in.neelesh.Email.Entity.User;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog, String>{

	List<SystemLog> findByUser(User user);
}
