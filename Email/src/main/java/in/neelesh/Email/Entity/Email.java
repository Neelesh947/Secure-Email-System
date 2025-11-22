package in.neelesh.Email.Entity;

import java.time.LocalDateTime;
import java.util.List;

import in.neelesh.Email.Enum.EmailStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {

	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false)
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", nullable = false)
	private User sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id", nullable = false)
	private User receiver;

	@Column(nullable = false)
	private String subject;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String encryptedBody;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EmailStatus status;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attachment> attachments;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
