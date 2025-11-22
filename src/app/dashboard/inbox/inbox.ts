import { Component, OnInit } from '@angular/core';
import { MailService } from '../../services/mail-service';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-inbox',
  standalone: false,
  templateUrl: './inbox.html',
  styleUrl: './inbox.scss',
})
export class Inbox implements OnInit{

  mails: any[] = [];
  searchTerm:string ="";

  constructor(private mailService: MailService, private auth:AuthService) { }

  ngOnInit() {
    this.loadInbox();
  }

  loadInbox() { }

  searchMails() {}

  logout(){
    this.auth.logout();
  }
}
