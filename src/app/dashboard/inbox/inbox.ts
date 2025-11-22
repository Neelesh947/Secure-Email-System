import { Component, OnInit } from '@angular/core';
import { MailService } from '../../services/mail-service';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-inbox',
  standalone: false,
  templateUrl: './inbox.html',
  styleUrl: './inbox.scss',
})
export class Inbox implements OnInit {

  mails: any[] = [];
  searchTerm: string = "";

  showCompose = false;
  compose = {
    to: '',
    subject: '',
    body: ''
  };

  constructor(private mailService: MailService, private auth: AuthService) { }

  ngOnInit() {
    this.loadInbox();
  }

  loadInbox() {
    this.mailService.getInbox().subscribe({
      next: (response: any) => {
        this.mails = response.emails || []; // map "emails" key
        console.log("mails:", this.mails);
      },
      error: (err) => {
        console.log("failed: -", err);
      }
    });
  }


  searchMails() { }

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
    this.mailService.sendEmail(this.compose.to, this.compose.subject, this.compose.body)
      .subscribe({
        next: (res) => {
          console.log('Email sent successfully:', res);
          alert('Email sent successfully!');
          this.closeCompose();
          this.loadInbox();
        },
        error: (err) => {
          console.error('Failed to send email:', err);
          alert('Error sending email. Please try again.');
        }
      });

  }
}
