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

  getAllUsers() {
    this.userService.getAllUsers().subscribe({
      next: (response: any) => {
        this.users = response.users || [];
        console.log("users list", this.users)
      }
    })
  }

  disable(id: string) {
    this.userService.disableUser(id).subscribe({
      next: () => this.loadUsers(this.selectedStatus),
      error: err => console.error(err)
    });
  }

  enable(id: string) {
    this.userService.enableUser(id).subscribe({
      next: () => this.loadUsers(this.selectedStatus),
      error: err => console.error(err)
    });
  }

  searchUsers() {
    if (this.searchTerm.trim() === "") {
      this.loadUsers(this.selectedStatus);
      return;
    }

    const term = this.searchTerm.toLowerCase();
    this.users = this.users.filter(u =>
      u.email.toLowerCase().includes(term)
    );
  }

  logout() {
    this.auth.logout();
  }

  filterUsers(status: string) {
    this.searchTerm = '';
    this.loadUsers(status);
  }

  loadUsers(status: string) {
    this.selectedStatus = status;

    if (status === 'All') {
      this.userService.getAllUsers().subscribe({
        next: res => this.users = res.users || [],
        error: err => console.error(err)
      });
    }

    if (status === 'Active') {
      this.userService.getActiveusers().subscribe({
        next: res => this.users = res.users || [],
        error: err => console.error(err)
      });
    }

    if (status === 'Inactive') {
      this.userService.getInactiveUsers().subscribe({
        next: res => this.users = res.users || [],
        error: err => console.error(err)
      });
    }
  }
}
