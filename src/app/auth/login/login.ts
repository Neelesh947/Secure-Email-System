import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  email = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  async login() {
    try {
      await this.auth.login(this.email, this.password);
      const role = this.auth.getRole();
      if (role === 'ADMIN') {
        this.router.navigate(['admin']);
      } else {
        this.router.navigate(['dashboard/inbox']);
      }
    } catch (e) {
      this.error = "Invalid login credentials";
    }
  }
}
