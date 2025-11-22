import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { MailService } from '../../services/mail-service';

@Component({
  selector: 'app-sent',
  standalone: false,
  templateUrl: './sent.html',
  styleUrl: './sent.scss',
})
export class Sent implements OnInit {

  searchTerm: string = '';
  sentMails: any[] = [];

  showCompose = false;
  compose = {
    to: '',
    subject: '',
    body: ''
  };

  constructor(private auth: AuthService, private mail: MailService) { }

  ngOnInit(): void {
    this.searchSentMails();
  }

  searchSentMails() {
    this.mail.getSent().subscribe({
      next: (response: any) => {
        this.sentMails = response.emails || [];;
        console.log("Mails: -", this.sentMails);
      },
      error: (err) => {
        console.log("failed: -", err);
      }
    })
  }

  logout() {
    this.auth.logout();
  }

  openCompose() {
    this.showCompose = true;
  }

  closeCompose() {
    this.showCompose = false;
    this.compose = { to: '', subject: '', body: '' };
  }

  sendEmail() {
    if (!this.compose.to || !this.compose.subject || !this.compose.body) {
      alert('Please fill all fields before sending.');
      return;
    }
    this.mail.sendEmail(this.compose.to, this.compose.subject, this.compose.body)
      .subscribe({
        next: (res) => {
          console.log('Email sent successfully:', res);
          alert('Email sent successfully!');
          this.closeCompose();
          this.searchSentMails();
        },
        error: (err) => {
          console.error('Failed to send email:', err);
          alert('Error sending email. Please try again.');
        }
      });

  }
}
