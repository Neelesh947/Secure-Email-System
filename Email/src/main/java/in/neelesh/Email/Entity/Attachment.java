package in.neelesh.Email.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attachments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false)
	private String uuid;

	@Column(nullable = false)
	private String filename;

	@Column(nullable = false)
	private String filePath;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	// The email this attachment belongs to
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email_id", nullable = false)
	private Email email;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}
