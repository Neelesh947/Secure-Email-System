package in.neelesh.Email.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.neelesh.Email.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByEmail(String email);
    boolean existsByEmail(String email);
}
