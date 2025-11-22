import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Inbox } from './dashboard/inbox/inbox';
import { Compose } from './dashboard/compose/compose';
import { Sent } from './dashboard/sent/sent';
import { ManageUsers } from './admin/manage-users/manage-users';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterseptor } from './services/token-interseptor';
import { FormsModule } from '@angular/forms';
import { JwtModule } from '@auth0/angular-jwt';

export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  declarations: [
    App,
    Login,
    Register,
    Inbox,
    Compose,
    Sent,
    ManageUsers
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
        allowedDomains: ["localhost:1234"], // your backend domain
        disallowedRoutes: ["http://localhost:1234/auth/login"] // optional
      }
    })
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterseptor, multi: true }
  ],
  bootstrap: [App]
})
export class AppModule { }