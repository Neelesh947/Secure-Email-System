import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MailService {

  private API_URL = 'http://localhost:1234/mail';

  constructor(private http: HttpClient) { }

  getInbox() {
    return this.http.get<any[]>(`${this.API_URL}/inbox`);
  }

  sendEmail(to: string, subject: string, body: string) {
    return this.http.post(`${this.API_URL}/send`, { to, subject, body });
  }

  getSent() {
    return this.http.get<any[]>(`${this.API_URL}/sent`);
  }
}
