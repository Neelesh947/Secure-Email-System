package in.neelesh.Email.Service;

import java.util.List;

import in.neelesh.Email.Dto.EmailSentDto;
import in.neelesh.Email.Entity.Email;
import in.neelesh.Email.Entity.User;

public interface EmailService {

	public Email sendEmail(User sender, User receiver, String subject, String body) throws Exception;

	public List<EmailSentDto> getListOfInbox(User user);

	public List<EmailSentDto> getListOfSentMessages(User user);
}
