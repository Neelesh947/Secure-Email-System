import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailInboxDto } from '../model/email-inbox-dto';

@Injectable({
  providedIn: 'root',
})
export class MailService {

  private API_URL = 'http://localhost:1234/email';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getInbox(): Observable<EmailInboxDto[]> {
    return this.http.get<EmailInboxDto[]>(`${this.API_URL}/list`, {
      headers: this.getAuthHeaders()
    });
  }


  sendEmail(receiver: string, subject: string, body: string): Observable<any> {
    return this.http.post(
      `${this.API_URL}/create`,
      { receiver, subject, body },
      { headers: this.getAuthHeaders() }
    );
  }


  getSent(): Observable<EmailInboxDto[]> {
    return this.http.get<EmailInboxDto[]>(`${this.API_URL}/sent`, {
      headers: this.getAuthHeaders()
    });
  }
}
