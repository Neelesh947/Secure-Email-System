package in.neelesh.Email.Service;

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
	
}
