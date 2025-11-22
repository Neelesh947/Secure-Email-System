package in.neelesh.Email.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.neelesh.Email.Dto.EmailSentDto;
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

	@Transactional
	public Email sendEmail(User sender, User receiver, String subject, String body) throws Exception {
		if (sender == null) {
			throw new IllegalArgumentException("Sender cannot be null");
		}
		if (receiver == null) {
			throw new IllegalArgumentException("Receiver cannot be null");
		}
		String encryptedBody = encryptionUtility.encrypt(body);
		Email email = Email.builder().sender(sender).receiver(receiver).subject(subject).encryptedBody(encryptedBody)
				.status(EmailStatus.SENT).build();
		return emailRepository.save(email);
	}

	public String readEmailBody(Email email) {
		try {
			return encryptionUtility.decrypt(email.getEncryptedBody());
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to decrypt";
		}
	}

	@Override
	public List<EmailSentDto> getListOfSentMessages(User user) {
		List<Email> sentEmails = emailRepository.findBySenderOrderByCreatedAtDesc(user);
		return mapEmailsToDto(sentEmails);
	}

	@Override
	public List<EmailSentDto> getListOfInbox(User user) {
		List<Email> inboxEmails = emailRepository.findByReceiverOrderByCreatedAtDesc(user);
		return mapEmailsToDto(inboxEmails);
	}

	/**
	 * Common method to map a list of Email entities to EmailSentDto
	 */
	private List<EmailSentDto> mapEmailsToDto(List<Email> emails) {
	    return emails.stream().map(email -> {
	        EmailSentDto dto = new EmailSentDto();
	        dto.setSenderId(email.getSender().getUuid());
	        dto.setReceiverId(email.getReceiver().getUuid());
	        dto.setReceiverEmail(email.getReceiver().getEmail());
	        dto.setEmailUUID(email.getUuid());
	        dto.setSubject(email.getSubject());
	        dto.setBody(readEmailBody(email));
	        dto.setStatus(email.getStatus().name());
	        dto.setCreatedAt(email.getCreatedAt().toString());
	        dto.setAttachments(email.getAttachments()); // or map to filenames if needed
	        return dto;
	    }).toList();
	}
}
