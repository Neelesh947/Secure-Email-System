import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-sent',
  standalone: false,
  templateUrl: './sent.html',
  styleUrl: './sent.scss',
})
export class Sent {

  searchTerm: string = '';
  sentMails: any[] = [];

  constructor(private auth: AuthService) { }

  searchSentMails() { }

  logout() {
    this.auth.logout();
  }
}
