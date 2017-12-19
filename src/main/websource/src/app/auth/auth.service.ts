import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';

@Injectable()
export class AuthenticationService {

  authenticatedUser: AuthenticatedUser;

  constructor(private http: HttpClient) {}

  private handleErrorPromise (error: Response | any) {
    console.error(error.message || error);
    return Promise.reject(error.message || error);
  }

  private handleErrorObservable (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }

  registerUser(user: User): void {
    const authUrl = `http://localhost:8080/Bookshelf/webresources/user`;
    const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

    this.http
      .post<AuthenticatedUser>(authUrl, user, { headers: header })
      .subscribe(x => {
        console.log('Fetched user: ', x.username);
        this.authenticatedUser = {
          username: x.username,
          hashedApiKey: x.hashedApiKey,
          expires: x.expires
        };
      });
  }

  public getAuthenticatedUser(): AuthenticatedUser {
    return this.authenticatedUser;
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
