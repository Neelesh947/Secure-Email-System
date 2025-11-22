package in.neelesh.Email.Dto;

import java.util.List;

import in.neelesh.Email.Entity.Attachment;
import lombok.Data;

@Data
public class EmailSentDto {

	private String senderId;
	private String receiverId;
	private String receiverEmail;
	private String emailUUID;
	private String subject;
	private String body;
	private String status;
	private String createdAt;
	private List<Attachment> attachments;
}
