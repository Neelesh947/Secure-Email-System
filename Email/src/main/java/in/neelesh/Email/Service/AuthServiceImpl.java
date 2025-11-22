package in.neelesh.Email.Service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Enum.Role;
import in.neelesh.Email.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User registerUser(Map<String, String> request) {
		String email = request.get("email");
		String password = request.get("password");
		String firstName = request.get("firstName");
		String lastName = request.get("lastName");
		String roleStr = request.get("role");
		if (userRepository.findByEmail(email) != null) {
			throw new RuntimeException("User with email " + email + " already exists");
		}
		String hashedPassword = passwordEncoder.encode(password);

		Role role;
		try {
			role = Role.valueOf(roleStr.toUpperCase());
		} catch (Exception e) {
			role = Role.USER;
		}

		User user = User.builder().email(email).password(hashedPassword).firstName(firstName).lastName(lastName)
				.role(role).status(true).build();
		return userRepository.save(user);
	}

}
