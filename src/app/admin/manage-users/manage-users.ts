import { Component } from '@angular/core';
import { UserService } from '../../services/user-service';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-manage-users',
  standalone: false,
  templateUrl: './manage-users.html',
  styleUrl: './manage-users.scss',
})
export class ManageUsers {

  users: any[] = [];
  searchTerm: string = '';
  selectedStatus: string = 'All';

  constructor(private userService: UserService, private auth: AuthService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() { }

  disable(id: number) { }

  searchUsers() { }

  logout() {
    this.auth.logout();
  }

  filterUsers(status: string) { }
}
