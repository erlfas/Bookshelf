import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { RegisteredUser } from '../models/registereduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { LoginUser } from '../models/loginuser.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  authenticatedUser: AuthenticatedUser;

  constructor(private formBuilder: FormBuilder, private http: Http) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      pwd: ['', Validators.required]
    });
  }

  onSubmit({ loginUser, valid }: { loginUser: LoginUser; valid: boolean }) {
    console.log(loginUser, valid);

    const authUrl = `localhost:8080/Bookshelf/webresources/authentication/json`;
    const authData = btoa(loginUser.email + ':' + loginUser.password);

    this.http
      .post(authUrl, JSON.stringify(new UserPassword(loginUser.email, loginUser.password)))
      .subscribe((res: Response) => {
        this.authenticatedUser = res.json();
      });

    const headers: Headers = new Headers();
    headers.append('API-KEY', this.authenticatedUser.hashedApiKey);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    const loginUrl = `localhost:8080/Bookshelf/webresources/user/login?username=${loginUser.email}&password=${loginUser.password}`;

    this.http
      .get(loginUrl)
      .subscribe((res: Response) => {
        if (res.ok) {
          
        }
      });
  }
}
