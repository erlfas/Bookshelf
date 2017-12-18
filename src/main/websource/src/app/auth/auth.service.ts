import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { UserPassword } from '../models/userpwd.model';

@Injectable()
export class AuthenticationService {
  authenticatedUser: AuthenticatedUser;

  constructor(private http: HttpClient) {}

  authenticate(username: string, password: string): Observable<AuthenticatedUser> {
    const authUrl = `localhost:8080/Bookshelf/webresources/user`;
    console.log('authenticate start');
    return this.http.post<AuthenticatedUser>(authUrl, JSON.stringify(new UserPassword(username, password)));
  }

  login(username: string, password: string): AuthenticatedUser {
    console.log('login start');
    this.authenticate(username, password).map(x => {
      console.log('login: ', x);
      if (x && x.hashedApiKey) {
        console.log('login 2: ', x);
        this.authenticatedUser = x;
        localStorage.setItem('currentUser', JSON.stringify(x));
      }
      console.log('login 3: ', x);
      return x;
    });

    return this.authenticatedUser;
  }

  getAuthenticatedUser(): AuthenticatedUser {
    return this.authenticatedUser;
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
