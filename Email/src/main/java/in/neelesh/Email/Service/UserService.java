package in.neelesh.Email.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User findById(String userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User findByEmail(String receiverEmail) {
		return userRepository.findByEmail(receiverEmail);
	}

	public List<User> getListOfUser() {
		return userRepository.findAll();
	}

	public void enableUser(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: - " + id));
		user.setStatus(true);
		userRepository.save(user);
	}

	public void disableUser(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		user.setStatus(false);
		userRepository.save(user);
	}

	public List<User> getListOfActiveUser() {
		return userRepository.findByStatusTrue();
	}

	public List<User> getListOfInActiveUser() {
		return userRepository.findByStatusFalse();
	}

}
