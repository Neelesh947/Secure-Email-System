package in.neelesh.Email.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.neelesh.Email.Dto.EmailSentDto;
import in.neelesh.Email.Entity.Email;
import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Service.EmailService;
import in.neelesh.Email.Service.UserService;
import in.neelesh.Email.Utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

	private final EmailService emailService;
	private final UserService userService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public ResponseEntity<?> sendEmails(@RequestBody Map<String, String> req) {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		String receiverEmail = req.get("receiver");
		String subject = req.getOrDefault("subject", "");
		String body = req.getOrDefault("body", "");

		User sender = userService.findById(userId);
		User receiver = userService.findByEmail(receiverEmail);
		if (receiver == null) {
			return ResponseEntity.badRequest().body(Map.of("error", "Receiver not found"));
		}

		try {
			Email email = emailService.sendEmail(sender, receiver, subject, body);

			return ResponseEntity.ok(Map.of("message", "Email sent successfully", "emailId", email.getUuid(),
					"timestamp", email.getCreatedAt()));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Map.of("error", "Email sending failed", "details", e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getTheEmailsList() {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.status(404).body(Map.of("error", "User not found"));
		}
		List<EmailSentDto> listOfInbox = emailService.getListOfInbox(user);
		return ResponseEntity.ok(Map.of("count", listOfInbox.size(), "emails", listOfInbox));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/sent")
	public ResponseEntity<Map<String, Object>> getTheSentList() {
		String userId = SecurityUtils.getCurrentUserIdSupplier.get();
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.status(404).body(Map.of("error", "User not found"));
		}
		List<EmailSentDto> listOfInbox = emailService.getListOfSentMessages(user);
		return ResponseEntity.ok(Map.of("count", listOfInbox.size(), "emails", listOfInbox));
	}
}
