import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { manageUsersGuard } from './guard/manage-users-guard';
import { Inbox } from './dashboard/inbox/inbox';
import { Compose } from './dashboard/compose/compose';
import { Sent } from './dashboard/sent/sent';
import { ManageUsers } from './admin/manage-users/manage-users';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  {
    path: 'dashboard',
    canActivate: [manageUsersGuard],
    children: [
      { path: 'inbox', component: Inbox },
      { path: 'compose', component: Compose },
      { path: 'sent', component: Sent },
    ]
  },

  {
    path: 'admin',
    canActivate: [manageUsersGuard],
    component: ManageUsers
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
