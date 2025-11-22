package in.neelesh.Email.Service;

import org.springframework.stereotype.Service;

import in.neelesh.Email.Entity.Email;
import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Enum.EmailStatus;
import in.neelesh.Email.Repository.EmailRepository;
import in.neelesh.Email.Utils.EncryptionUtility;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final EmailRepository emailRepository;
	private final EncryptionUtility encryptionUtility;

	public Email sendEmail(User sender, User receiver, String subject, String body) throws Exception {
		String encryptedBody = encryptionUtility.encrypt(body);
		Email email = Email.builder().sender(sender).receiver(receiver).subject(subject).encryptedBody(encryptedBody)
				.status(EmailStatus.SENT).build();
		return emailRepository.save(email);
	}

	public String readEmail(Email email) throws Exception {
		return encryptionUtility.decrypt(email.getEncryptedBody());
	}

}
