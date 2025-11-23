import { Component } from '@angular/core';
import { MailService } from '../../services/mail-service';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-compose',
  standalone: false,
  templateUrl: './compose.html',
  styleUrl: './compose.scss',
})
export class Compose {

  to = '';
  subject = '';
  body = '';
  searchTerm: string = '';

  constructor(private mailService: MailService, private auth:AuthService) {}

  send() {  }

  searchMails(){
    
  }

  logout() {
    this.auth.logout();
  }
}
