import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import axios from 'axios';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private API_URL = "http://localhost:1234/auth";
  token: string = '';
  role: string = '';

  constructor(private jwtHelper: JwtHelperService, private router: Router) { }

  async login(email: string, password: string) {
    const res = await axios.post(`${this.API_URL}/login`, { email, password });
    this.token = res.data.token;
    localStorage.setItem('token', res.data.token);
    const decoded: any = this.jwtHelper.decodeToken(this.token);
    this.role = decoded.role;
    localStorage.setItem('role', this.role);

    return res.data;
  }

  async register(firstName: string, lastName: string, email: string, password: string) {
    return axios.post(`${this.API_URL}/register`, { email, password, firstName, lastName });
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.token = '';
    this.role = '';
    window.location.reload();
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getRole(): string {
    return this.role || localStorage.getItem('role') || '';
  }
}
