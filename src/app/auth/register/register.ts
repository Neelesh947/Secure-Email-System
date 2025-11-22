import { Component } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  error: string = '';

  constructor(private auth:AuthService, private router:Router){}

  register() { 

    this.error = '';

    if (!this.firstName || !this.lastName || !this.email || !this.password) {
      this.error = 'All fields are required';
      return;
    }

    try {
      this.auth.register(this.firstName, this.lastName, this.email, this.password);
      this.router.navigate(['/login']);
    } catch (error) {
      console.error(error);
      alert("Registration Failed");
    }
    

  }
}
