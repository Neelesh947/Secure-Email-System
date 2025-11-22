package in.neelesh.Email.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Service.AuthService;
import in.neelesh.Email.security.jwtUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final PasswordEncoder passwordEncoder;
	private final jwtUtils jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
		String email = req.get("email");
		String password = req.get("password");

		if (email == null || password == null) {
			return ResponseEntity.badRequest().body("Email and password must be provided");
		}

		User user = authService.findByEmail(email);
		if (user == null) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}
		if (!passwordEncoder.matches(password, user.getPassword())) {
			return ResponseEntity.status(401).body("Invalid credentials");
		}

		String token = jwtUtil.generateToken(user.getRole().name(), user.getEmail(), user.getUuid(),
				user.getFullName());
		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Map<String, String> req) {

		User user = authService.registerUser(req);
		return ResponseEntity.ok("User registered successfully");
	}
}
