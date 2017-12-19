import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';

@Injectable()
export class AuthenticationService {

  authenticatedUser: AuthenticatedUser;

  constructor(
    private http: HttpClient,
    private router: Router) {}

  loginUser(username: string, password: string) {
    console.log('AuthenticationService: loginUser: ', username, password);
    const url = `http://localhost:8080/Bookshelf/webresources/user/login?username=${username}&password=${password}`;
    const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

    this.http
      .get<AuthenticatedUser>(url)
      .subscribe(
        data => {
          localStorage.setItem('currentUser', JSON.stringify(data));
          this.router.navigate(['/bookshelf']);
        },
        err => {
          console.log('AuthenticationService: loginUser: error');
          this.router.navigate(['/home']);
        },
        () => {}
      );
  }

  registerUser2(user: User): Observable<AuthenticatedUser> {
    const authUrl = `http://localhost:8080/Bookshelf/webresources/user`;
    const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

    return this.http.post<AuthenticatedUser>(authUrl, user, { headers: header });
  }

  registerUser(user: User): void {
    const authUrl = `http://localhost:8080/Bookshelf/webresources/user`;
    const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

    this.http
      .post<AuthenticatedUser>(authUrl, user, { headers: header })
      .subscribe(
        data => {
          console.log('Fetched user: ', data.username);
          this.authenticatedUser = {
            username: data.username,
            hashedApiKey: data.hashedApiKey,
            expires: data.expires
          };
      },
      err => {
        console.log('error');
      });
  }

  getAuthenticatedUser(): AuthenticatedUser {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
