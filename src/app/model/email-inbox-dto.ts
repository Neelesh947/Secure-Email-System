export interface EmailInboxDto {
  emailUUID: string;        // Unique ID of the email
  senderId: string;         // UUID of the sender
  senderEmail: string;      // Email address of the sender
  subject: string;
  body: string;             // Decrypted body
  status: string;           // e.g., SENT, READ
  createdAt: string;        // ISO date string
  attachments: string[];    // Could be filenames or URLs
}
