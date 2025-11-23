package in.neelesh.Email.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Service.UserService;
import in.neelesh.Email.Utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user-list")
	ResponseEntity<?> getTheListOfUser() {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		List<User> userList = userService.getListOfUser();
		return ResponseEntity.ok(Map.of("count", userList.size(), "users", userList));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/active-list")
	ResponseEntity<?> getTheListOfActiveUser() {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		List<User> userList = userService.getListOfActiveUser();
		return ResponseEntity.ok(Map.of("count", userList.size(), "users", userList));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/inactive-list")
	ResponseEntity<?> getTheListOfInActiveUser() {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		List<User> userList = userService.getListOfInActiveUser();
		return ResponseEntity.ok(Map.of("count", userList.size(), "users", userList));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/users/enable/{id}")
	public ResponseEntity<?> enableUser(@PathVariable String id) {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		try {
			userService.enableUser(id);
			return ResponseEntity.ok(Map.of("message", "User enabled successfully"));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/users/disable/{id}")
	public ResponseEntity<?> disableUser(@PathVariable String id) {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		try {
			userService.disableUser(id);
			return ResponseEntity.ok(Map.of("message", "User enabled successfully"));
		} catch (Exception e) {
			return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
		}
	}
}
