package in.neelesh.Email.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.neelesh.Email.Entity.LoginHistory;
import in.neelesh.Email.Entity.User;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, String>{

	List<LoginHistory> findByUser(User user);
}
