import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  private API_URL = 'http://localhost:1234/admin';

  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token') || '';
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.API_URL}/user-list`, {
      headers: this.getAuthHeaders()
    });
  }

  getActiveusers(): Observable<any> {
    return this.http.get(`${this.API_URL}/active-list`, {
      headers: this.getAuthHeaders()
    });
  }

  getInactiveUsers(): Observable<any> {
    return this.http.get(`${this.API_URL}/inactive-list`, {
      headers: this.getAuthHeaders()
    });
  }

  enableUser(id: string): Observable<any> {
    return this.http.put(`${this.API_URL}/users/enable/${id}`, {}, {
      headers: this.getAuthHeaders()
    });
  }

  disableUser(id: string): Observable<any> {
    return this.http.put(`${this.API_URL}/users/disable/${id}`, {}, {
      headers: this.getAuthHeaders()
    });
  }
}
